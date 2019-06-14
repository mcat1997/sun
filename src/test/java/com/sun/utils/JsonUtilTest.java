package com.sun.utils;

import com.sun.controller.dto.RequestDto;
import com.sun.controller.dto.ResponseDto;
import com.sun.entity.Answer;
import com.sun.entity.Question;
import com.sun.entity.Questionnaire;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JsonUtilTest {
    @Test
    public void toJson() {
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setAccountId(15);
        questionnaire.setQuestionnaireName("hello");
        RequestDto requestDto = new RequestDto();
        requestDto.setQuestionnaire(questionnaire);
        System.out.println(JsonUtil.toJson(requestDto));
    }

    @Test
    public void fromJson() {
        String json = "{\"questionnaire\":{\"accountId\":15,\"questionnaireName\":\"hello\"}}";
        RequestDto requestDto = (RequestDto) JsonUtil.fromJson(json, new RequestDto());
        System.out.println(requestDto.getQuestionnaire().getAccountId());
        System.out.println(requestDto.getQuestionnaire().getQuestionnaireName());
    }

    @Test
    public void fromJson2() {
        String json = "[{\"questionId\":10,\"answerText\":\"123\"},{\"questionId\":11,\"answerText\":\"1\"},{\"questionId\":11,\"answerText\":\"2\"},{\"questionId\":12,\"answerText\":\"1\"},{\"questionId\":15,\"answerText\":\"1\"},{\"questionId\":16,\"answerText\":\"1\"},{\"questionId\":17,\"answerText\":\"123\"},{\"questionId\":18,\"answerText\":\"1\"}]";
        List<Answer> answers = (List<Answer>) JsonUtil.fromJson2(json);
        System.out.println(answers.get(0).getAnswerText());
    }

    @Test
    public void test() {
        Map<String, Integer> map = new HashMap<>();
        map.put("1", 1);
        map.put("1", map.get("1") + 1);
        System.out.println(map.get("2"));
//        List<Integer> list = new ArrayList<>();
//        list.add(1);
//        list.add(2);
//        String json = JsonUtil.toJson(list);
//        List<Integer> list2 = (List<Integer>) JsonUtil.fromJson(json,new ArrayList<>());
//        System.out.println(list2.get(0));
    }
}
