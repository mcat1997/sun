package com.sun.service;

import com.sun.controller.dto.ResponseDto;
import com.sun.entity.Question;
import com.sun.entity.Questionnaire;
import com.sun.mapper.QuestionMapper;
import com.sun.mapper.QuestionnaireMapper;
import com.sun.utils.ConstantUtil;
import com.sun.utils.JsonUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Service
public class QuestionService {
    @Resource
    QuestionMapper questionMapper;
    @Resource
    QuestionnaireMapper questionnaireMapper;

    public ResponseDto insertOneQuestion(Integer type, String questionTitle, String answerStyle, HttpSession session) {
        ResponseDto responseDto = new ResponseDto();
        if (type == null || StringUtils.isEmpty(questionTitle) || ObjectUtils.isEmpty(session)) {
            responseDto.setSuccess(ConstantUtil.NO_SUCCESS);
            responseDto.setAlertStr("参数错误1");
            return responseDto;
        }
        Questionnaire questionnaire = (Questionnaire) session.getAttribute("questionnaire");
        if (ObjectUtils.isEmpty(questionnaire)) {
            responseDto.setSuccess(ConstantUtil.NO_SUCCESS);
            responseDto.setAlertStr("参数错误2");
            return responseDto;
        }
        Integer questionnaireId = questionnaire.getId();
        if (ObjectUtils.isEmpty(questionnaire)) {
            responseDto.setSuccess(ConstantUtil.NO_SUCCESS);
            responseDto.setAlertStr("参数错误");
            return responseDto;
        }
        if (type.equals(ConstantUtil.QUESTION_MULTIPLE_SELECTION) || type.equals(ConstantUtil.QUESTION_SINGLE_SELECTION)) {
            if (StringUtils.isEmpty(answerStyle)) {
                responseDto.setSuccess(ConstantUtil.NO_SUCCESS);
                responseDto.setAlertStr("答案样式不能为空");
                return responseDto;
            }
        }
        Integer serial_number = questionMapper.selectMaxSerialByQuestionnaireId(questionnaireId) + 1;
        if (StringUtils.isEmpty(answerStyle)) {
            answerStyle = "";
        }
        List<String> answerStyles = Arrays.asList(answerStyle.split(";"));
        Question question = new Question();
        question.setType(type);
        question.setQuestionTitle(questionTitle);
        question.setSerialNumber(serial_number);
        question.setAnswerStyle(JsonUtil.toJson(answerStyles));
        question.setQuestionnaireId(questionnaireId);
        if (questionMapper.insertOne(question) < 1) {
            responseDto.setSuccess(ConstantUtil.NO_SUCCESS);
            responseDto.setAlertStr("数据库错误");
            return responseDto;
        }
        questionnaireMapper.updateQuestionNumById(questionnaireId);
        responseDto.setSuccess(ConstantUtil.IS_SUCCESS);
        responseDto.setAlertStr("添加成功");
        return responseDto;
    }
}
