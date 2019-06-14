package com.sun.mapper;

import com.sun.entity.Question;
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
public class QuestionMapperTest {

    @Resource
    private QuestionMapper questionMapper;

    @Test
    public void selectAll() {
        System.out.println("select all");
        questionMapper.selectAll().forEach(question -> System.out.println(JsonUtil.toJson(question)));

    }

    @Test
    public void insertOne() {
        Question question = new Question();
        question.setQuestionnaireId(1);
        question.setSerialNumber(1);
        question.setType(ConstantUtil.QUESTION_TRUE_OR_FALSE);
        question.setQuestionTitle("刘潇是帅哥");
        question.setAnswerStyle("");
//        System.out.println("insertOne");
//        questionMapper.insertOne(question);
//        selectAll();
    }

    @Test
    public void insertList() {
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
        System.out.println("insertLis");
        questionMapper.insertList(questions);
        selectAll();
    }

    @Test
    public void selectMaxSerialByQuestionnaireId() {
        System.out.println(questionMapper.selectMaxSerialByQuestionnaireId(1));
        System.out.println(questionMapper.selectMaxSerialByQuestionnaireId(2));
    }

    @Test
    public void selectByQuestionnaireId() {
        questionMapper.selectByQuestionnaireId(0).forEach(question -> System.out.println(JsonUtil.toJson(question)));
        System.out.println();
        questionMapper.selectByQuestionnaireId(1).forEach(question -> System.out.println(JsonUtil.toJson(question)));
    }
}
