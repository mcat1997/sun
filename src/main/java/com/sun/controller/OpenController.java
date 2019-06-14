package com.sun.controller;

import com.sun.controller.dto.ResponseDto;
import com.sun.entity.Questionnaire;
import com.sun.service.QuestionnaireService;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class OpenController {

    @Resource
    QuestionnaireService questionnaireService;

    @GetMapping("getQuestionnaire")
    public ModelAndView getQuestionnaire(
            @RequestParam("questionnaireId") Integer questionnaireId,
            HttpSession session
    ) {
        return questionnaireService.getQuestionnaire(questionnaireId, session);
    }

    @GetMapping("thanks")
    public ModelAndView thanks() {
        Map<String, String> map = new HashMap<>();
        map.put("alertStr", "谢谢");
        return new ModelAndView("alert", map);
    }

    @GetMapping("did")
    public ModelAndView did() {
        Map<String, String> map = new HashMap<>();
        map.put("alertStr", "已经做过该问卷");
        return new ModelAndView("alert", map);
    }

@ResponseBody
@PostMapping("answerQuestionnaire")
public void answerQuestionnaire(
        @RequestParam("questionnaireId") Integer questionnaireId,
        @RequestParam("answersJson") String answersJson,
        HttpSession session
) {
    questionnaireService.answerQuestionnaire(questionnaireId, answersJson, session);
}
}
