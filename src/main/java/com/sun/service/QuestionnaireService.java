package com.sun.service;

import ch.qos.logback.classic.pattern.ClassNameOnlyAbbreviator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.sun.controller.dto.RequestDto;
import com.sun.controller.dto.ResponseDto;
import com.sun.entity.Account;
import com.sun.entity.Answer;
import com.sun.entity.Question;
import com.sun.entity.Questionnaire;
import com.sun.mapper.AccountMapper;
import com.sun.mapper.AnswerMapper;
import com.sun.mapper.QuestionMapper;
import com.sun.mapper.QuestionnaireMapper;
import com.sun.utils.ConstantUtil;
import com.sun.utils.JsonUtil;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.*;

@Service
public class QuestionnaireService {
    @Resource
    QuestionnaireMapper questionnaireMapper;
    @Resource
    QuestionMapper questionMapper;
    @Resource
    AnswerMapper answerMapper;
    @Resource
    AccountMapper accountMapper;

    public ResponseDto insertOneQuestionnaire(String questionnaireName, HttpSession session) {
//        System.out.println("insert one");
        ResponseDto responseDto = new ResponseDto();
        if (StringUtils.isEmpty(questionnaireName)) {
            responseDto.setSuccess(ConstantUtil.NO_SUCCESS);
            responseDto.setAlertStr("问卷名不能为空");
            return responseDto;
        }
        Account account = (Account) session.getAttribute("account");
        if (ObjectUtils.isEmpty(account) || ObjectUtils.isEmpty(account.getId())) {
            responseDto.setSuccess(ConstantUtil.NO_SUCCESS);
            responseDto.setAlertStr("未知错误");
            return responseDto;
        }
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setQuestionnaireName(questionnaireName);
        questionnaire.setAccountId(account.getId());
        if (questionnaireMapper.insertOne(questionnaire) < 1) {
            responseDto.setSuccess(ConstantUtil.NO_SUCCESS);
            responseDto.setAlertStr("数据库错误");
            return responseDto;
        }
        responseDto.setSuccess(ConstantUtil.IS_SUCCESS);
        responseDto.setAlertStr("成功");
        session.setAttribute("questionnaire", questionnaire);
        return responseDto;
    }

    public ResponseDto insertOneQuestion(String requestDtoJson) {
        ResponseDto responseDto = new ResponseDto();
        RequestDto requestDto = (RequestDto) JsonUtil.fromJson(requestDtoJson, new RequestDto());
        if (ObjectUtils.isEmpty(requestDto) ||
                ObjectUtils.isEmpty(requestDto.getQuestion()) ||
                !requestDto.getQuestion().isValued()) {
            responseDto.setSuccess(ConstantUtil.NO_SUCCESS);
            responseDto.setAlertStr("参数错误");
            return responseDto;
        }
        if (questionMapper.insertOne(requestDto.getQuestion()) < 1) {
            responseDto.setSuccess(ConstantUtil.NO_SUCCESS);
            responseDto.setAlertStr("数据库错误");
            return responseDto;
        }
        questionnaireMapper.updateQuestionNumById(requestDto.getQuestion().getQuestionnaireId());
        responseDto.setSuccess(ConstantUtil.IS_SUCCESS);
        return responseDto;
    }

//    public ResponseDto insertQuestions(String requestDtoJson) {
//        ResponseDto responseDto = new ResponseDto();
//        RequestDto requestDto = (RequestDto) JsonUtil.fromJson2(requestDtoJson, new RequestDto());
//        if (ObjectUtils.isEmpty(requestDto) ||
//                ObjectUtils.isEmpty(requestDto.getQuestions())) {
//            responseDto.setSuccess(ConstantUtil.NO_SUCCESS);
//            responseDto.setAlertStr("参数错误1");
//            return responseDto;
//        }
//        List<Question> questions = requestDto.getQuestions();
//        Boolean flag = true;
//        for (int i = 0; i < questions.size(); i++) {
//            if (!questions.get(i).isValued()) {
//                flag = false;
//            }
//        }
//        if (!flag) {
//            responseDto.setSuccess(ConstantUtil.NO_SUCCESS);
//            responseDto.setAlertStr("参数错误1");
//            return responseDto;
//        }
//        Integer insertNum = questionMapper.insertList(questions);
//        questionnaireMapper.updateQuestionNumByQuestionNumAndId(insertNum, questions.get(0).getQuestionnaireId());
//        return responseDto;
//    }

    public Map questionnaireInfos(Integer pageNum, HttpSession session) {
        List<Questionnaire> questionnaires;
        Account account = (Account) session.getAttribute("account");
        if (ObjectUtils.isEmpty(account) || account.getId() == null) {
            return null;
        }
        if (pageNum == null || pageNum < 1) {
            pageNum = 1;
        }
        Integer pageCount = questionnaireMapper.selectNumByAccountId(account.getId()) / ConstantUtil.PAGE_SIZE + 1;
        PageHelper.startPage(pageNum, ConstantUtil.PAGE_SIZE);
        PageHelper.orderBy("id desc");
        questionnaires = questionnaireMapper.selectByAccountId(account.getId());

        Map<String, Object> result = new HashMap<>();
        result.put("pageNum", pageNum);
        result.put("pageCount", pageCount);
        result.put("questionnaires", JsonUtil.toJson(questionnaires));

        return result;
    }

    public Map editQuestionnaire(Integer questionnaireId, HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        Questionnaire questionnaire = null;
        if (questionnaireId == null) {
            questionnaire = (Questionnaire) session.getAttribute("questionnaire");
        } else {
            questionnaire = questionnaireMapper.selectById(questionnaireId);
        }
        List<Question> questions = questionMapper.selectByQuestionnaireId(questionnaire.getId());

        List<Integer> questionIds = new ArrayList<>();
        List<Integer> selectType = new ArrayList<>();
        selectType.add(ConstantUtil.QUESTION_TRUE_OR_FALSE);
        selectType.add(ConstantUtil.QUESTION_MULTIPLE_SELECTION);
        selectType.add(ConstantUtil.QUESTION_SINGLE_SELECTION);
        questions.forEach(question -> {
            if (selectType.contains(question.getType())) {
                questionIds.add(question.getId());
            }
        });
        List<Answer> answers;
        if (!questionIds.isEmpty()) {
            answers = answerMapper.selectByQuestionIds(questionIds);
        } else {
            answers = new ArrayList<>();
        }

        Map<String, Integer> answerResultMap = new HashMap<>();
        answers.forEach(answer -> {
            String key = answer.getQuestionId() + answer.getAnswerText();
            if (!answerResultMap.containsKey(key)) {
                answerResultMap.put(key, 1);
            } else {
                answerResultMap.put(key, answerResultMap.get(key) + 1);
            }
        });


        questions.forEach(question -> {
            if (question.getType().equals(ConstantUtil.QUESTION_TRUE_OR_FALSE)) {
                List<Integer> answerResult = new ArrayList<>();
                answerResult.add(answerResultMap.getOrDefault(question.getId() + "1", 0));
                answerResult.add(answerResultMap.getOrDefault(question.getId() + "2", 0));
                question.setAnswerResult(JsonUtil.toJson(answerResult));
            } else if (question.getType().equals(ConstantUtil.QUESTION_SINGLE_SELECTION) ||
                    question.getType().equals(ConstantUtil.QUESTION_MULTIPLE_SELECTION)) {
                List<Integer> answerResult = new ArrayList<>();
                List<String> answerStyleList = (List<String>) JsonUtil.fromJson(question.getAnswerStyle(), new ArrayList<>());
                for (int i = 1; i < answerStyleList.size() + 1; i++) {
                    String key = "" + question.getId() + i;
                    answerResult.add(answerResultMap.getOrDefault(key, 0));
                }
                question.setAnswerResult(JsonUtil.toJson(answerResult));
            } else {
                List<Integer> answerResult = new ArrayList<>();
                question.setAnswerResult(JsonUtil.toJson(answerResult));
            }
        });

        session.setAttribute("questionnaire", questionnaire);
        result.put("questions", JsonUtil.toJson(questions));
        return result;
    }

    public ModelAndView getQuestionnaire(Integer questionnaireId, HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        if (questionnaireId == null) {
            result.put("isSuccess", ConstantUtil.NO_SUCCESS);
            result.put("alertStr", "参数错误");
            return new ModelAndView("alert", result);
        }
        List<Integer> didQuestionnaires = new ArrayList<>();
        if (!ObjectUtils.isEmpty(session.getAttribute("didQuestionnaires"))) {
            didQuestionnaires = (List<Integer>) session.getAttribute("didQuestionnaires");
        }
        if (didQuestionnaires.contains(questionnaireId)) {
            result.put("isSuccess", ConstantUtil.NO_SUCCESS);
            result.put("alertStr", "已做过该问卷");
            return new ModelAndView("alert", result);
        }

        Questionnaire questionnaire = questionnaireMapper.selectById(questionnaireId);
        if (questionnaire.getBanded().equals(ConstantUtil.IS_BANDED)) {
            result.put("isSuccess", ConstantUtil.NO_SUCCESS);
            result.put("alertStr", "问卷已被封禁");
            return new ModelAndView("alert", result);
        }
        List<Question> questions = questionMapper.selectByQuestionnaireId(questionnaireId);

        result.put("isSuccess", ConstantUtil.IS_SUCCESS);
        result.put("questionnaire", questionnaire);
        result.put("questions", JsonUtil.toJson(questions));

        return new ModelAndView("getQuestionnaire", result);
    }

    public void answerQuestionnaire(Integer questionnaireId, String answersJson, HttpSession session) {
        List<Answer> answers = (List<Answer>) JsonUtil.fromJson2(answersJson);
        List<Integer> didQuestionnaires = new ArrayList<>();
        if (!ObjectUtils.isEmpty(session.getAttribute("didQuestionnaires"))) {
            didQuestionnaires = (List<Integer>) session.getAttribute("didQuestionnaires");
        }
        if (didQuestionnaires.contains(questionnaireId)) {
            session.setAttribute("alertStr", "你已做过该问卷");
        } else {
            didQuestionnaires.add(questionnaireId);
            session.setAttribute("didQuestionnaires", didQuestionnaires);
            answerMapper.insertPatch(answers);
            questionnaireMapper.updateAnswerNumById(questionnaireId);
            session.setAttribute("alertStr", "谢谢");
        }
    }

    public ModelAndView questionnaireInfos2(Integer pageNum) {
        List<Questionnaire> questionnaires = new ArrayList<>();
        if (pageNum == null || pageNum < 1) {
            pageNum = 1;
        }
        Integer pageCount = questionnaireMapper.selectNum() / ConstantUtil.PAGE_SIZE + 1;
        PageHelper.startPage(pageNum, ConstantUtil.PAGE_SIZE);
        PageHelper.orderBy("id desc");
        questionnaires = questionnaireMapper.selectAll();

        Map<String, Object> result = new HashMap<>();
        result.put("pageNum", pageNum);
        result.put("pageCount", pageCount);
        result.put("questionnaires", JsonUtil.toJson(questionnaires));
        return new ModelAndView("questionnaireInfos2", result);
    }

    public Map subjectAnswerInfos(Integer pageNum, Integer questionId) {
        if (pageNum == null || pageNum < 1) {
            pageNum = 1;
        }
        if (questionId == null) {
            questionId = 0;
        }
//        List<Answer> answers = answerMapper.selectByQuestionIds(Collections.singletonList(questionId));
        Integer pageCount = answerMapper.selectNumByQuestionIds(Collections.singletonList(questionId)) / ConstantUtil.PAGE_SIZE + 1;
        PageHelper.startPage(pageNum, ConstantUtil.PAGE_SIZE);
        PageHelper.orderBy("id desc");
        List<Answer> answers = answerMapper.selectByQuestionIds(Collections.singletonList(questionId));

        Map<String, Object> result = new HashMap<>();
        result.put("pageNum", pageNum);
        result.put("pageCount", pageCount);
        result.put("answers", JsonUtil.toJson(answers));
        result.put("questionId", questionId);
        return result;
    }

    public Map banItem(Integer questionnaireId, Integer accountId) {
        if (questionnaireId != null) {
            questionnaireMapper.updateBandedById(questionnaireId, ConstantUtil.IS_BANDED);
        } else if (accountId != null) {
            accountMapper.updateBandedById(accountId, ConstantUtil.IS_BANDED);
        }
        Map<String, String> map = new HashMap<>();
        map.put("alertStr", "封禁成功");
        return map;
    }
}
