package com.sun.service;

import com.google.gson.JsonObject;
import com.sun.controller.dto.RequestDto;
import com.sun.entity.Question;
import com.sun.entity.Questionnaire;
import com.sun.utils.ConstantUtil;
import com.sun.utils.JsonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuestionnaireServiceTest {
    @Resource
    QuestionnaireService questionnaireService;

    @Test
    public void insertOneQuestionnaire() {
        RequestDto requestDto = new RequestDto();
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setAccountId(16);
        questionnaire.setQuestionnaireName("刘潇调查问卷");
        requestDto.setQuestionnaire(questionnaire);
//        System.out.println(JsonUtil.toJson(questionnaireService.insertOneQuestionnaire(JsonUtil.toJson(requestDto))));
    }

    @Test
    public void insertQuestions() {
        Question question1 = new Question();
        question1.setQuestionnaireId(1);
        question1.setSerialNumber(2);
        question1.setType(ConstantUtil.QUESTION_TRUE_OR_FALSE);
        question1.setQuestionTitle("刘潇是帅哥");
        question1.setAnswerStyle("");

        Question question2 = new Question();
        question2.setQuestionnaireId(1);
        question2.setSerialNumber(3);
        question2.setType(ConstantUtil.QUESTION_TRUE_OR_FALSE);
        question2.setQuestionTitle("刘潇是帅哥");
        question2.setAnswerStyle("");

        List<Question> questions = new ArrayList<>();
        questions.add(question1);
        questions.add(question2);

        RequestDto requestDto = new RequestDto();
        requestDto.setQuestions(questions);

        String requestDtoJson = JsonUtil.toJson(requestDto);
//        System.out.println(JsonUtil.toJson(questionnaireService.insertQuestions(requestDtoJson)));
    }
}
