package com.sun.mapper;

import com.sun.entity.Answer;
import com.sun.utils.JsonUtil;
import org.eclipse.jdt.internal.compiler.ast.AND_AND_Expression;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AnswerMapperTest {
    @Resource
    private AnswerMapper answerMapper;

    @Test
    public void selectAll() {
        System.out.println("select all");
        answerMapper.selectAll().forEach(answer -> System.out.println(JsonUtil.toJson(answer)));
    }

    @Test
    public void insertPatch() {
        Answer answer1 = new Answer();
        answer1.setQuestionId(16);
        answer1.setAnswerText("非常帅");
        Answer answer2 = new Answer();
        answer2.setQuestionId(16);
        answer2.setAnswerText("特别帅");
        Answer answer3 = new Answer();
        answer3.setQuestionId(16);
        answer3.setAnswerText("");
        List<Answer> answers = new ArrayList<>();
        answers.add(answer1);
        answers.add(answer2);
        answers.add(answer3);
        answerMapper.insertPatch(answers);
    }

    @Test
    public void selectByQuestionIds() {
        List<Integer> questionIds = new ArrayList<>();
        questionIds.add(20);
        questionIds.add(21);
        questionIds.add(0);
        List<Answer> answers = answerMapper.selectByQuestionIds(questionIds);
        System.out.println(JsonUtil.toJson(answers));
    }

    @Test
    public void selectNumByQuestionIds() {
        System.out.println(answerMapper.selectNumByQuestionIds(Collections.singletonList(19)));
    }
}
