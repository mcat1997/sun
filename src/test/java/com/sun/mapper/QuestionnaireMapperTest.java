package com.sun.mapper;

import com.sun.entity.Questionnaire;
import com.sun.utils.ConstantUtil;
import com.sun.utils.JsonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuestionnaireMapperTest {
    @Resource
    private QuestionnaireMapper questionnaireMapper;

    @Test
    public void selectAll() {
        System.out.println("select all");
        questionnaireMapper.selectAll().forEach(questionnaire -> System.out.println(JsonUtil.toJson(questionnaire)));
    }

    @Test
    public void insertOne() {
        System.out.println("insertOne");
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setAccountId(15);
        questionnaire.setQuestionnaireName("hello");
        questionnaireMapper.insertOne(questionnaire);
        System.out.println(questionnaire.getId());
        selectAll();
    }

    @Test
    public void upQuestionNumById() {
        System.out.println("updateQuestionNumById");
        questionnaireMapper.updateQuestionNumById(1);
        selectAll();
    }

    @Test
    public void updateQuestionNumByQuestionNumAndId() {
        System.out.println("updateQuestionNumByQuestionNumAndId");
        questionnaireMapper.updateQuestionNumByQuestionNumAndId(2,1);
        selectAll();
    }

    @Test
    public void selectNumByAccountId() {
        System.out.println("selectNumByAccountId");
        System.out.println(questionnaireMapper.selectNumByAccountId(1));
        System.out.println(questionnaireMapper.selectNumByAccountId(15));
    }

    @Test
    public void selectByAccountId() {
        System.out.println("selectByAccountId");
        questionnaireMapper.selectByAccountId(0).forEach(questionnaire -> System.out.println(JsonUtil.toJson(questionnaire)));
        questionnaireMapper.selectByAccountId(15).forEach(questionnaire -> System.out.println(JsonUtil.toJson(questionnaire)));
    }

    @Test
    public void selectById() {
        System.out.println("selectById");
        System.out.println(JsonUtil.toJson(questionnaireMapper.selectById(0)));
        System.out.println(JsonUtil.toJson(questionnaireMapper.selectById(1)));
    }

    @Test
    public void selectNum() {
        System.out.println(questionnaireMapper.selectNum());
    }

    @Test
    public void updateBandedById(){
        questionnaireMapper.updateBandedById(18, ConstantUtil.NO_BANDED);
    }
}
