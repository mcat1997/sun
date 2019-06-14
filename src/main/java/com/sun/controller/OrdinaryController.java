package com.sun.controller;

import com.sun.controller.dto.ResponseDto;
import com.sun.entity.Questionnaire;
import com.sun.service.QuestionService;
import com.sun.service.QuestionnaireService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class OrdinaryController {

    @Resource
    QuestionnaireService questionnaireService;
    @Resource
    QuestionService questionService;

    @GetMapping("/ordinary")
    public ModelAndView ordinary() {
        return new ModelAndView("ordinary");
    }

    @ResponseBody
    @PostMapping("insertOneQuestionnaire")
    public ResponseDto insertOneQuestionnaire(
            @RequestParam("questionnaireName") String questionnaireName,
            HttpSession session
    ) {
        return questionnaireService.insertOneQuestionnaire(questionnaireName, session);
    }

    @GetMapping("/editQuestionnaire")
    public ModelAndView editQuestionnaire(
            @Param("questionnaireId") Integer questionnaireId,
            HttpSession session
    ) {
        return new ModelAndView("editQuestionnaire", questionnaireService.editQuestionnaire(questionnaireId, session));
    }

    @ResponseBody
    @PostMapping("insertOneQuestion")
    public ResponseDto insertOneQuestion(
            @RequestParam("type") Integer type,
            @RequestParam("questionTitle") String questionTitle,
            @Param("answerStyle") String answerStyle,
            HttpSession session
    ) {
        return questionService.insertOneQuestion(type, questionTitle, answerStyle, session);
    }

    @GetMapping("questionnaireInfos")
    public ModelAndView questionnaireInfos(@Param("pageNum") Integer pageNum, HttpSession session) {
        return new ModelAndView("questionnaireInfos", questionnaireService.questionnaireInfos(pageNum, session));
    }

    @GetMapping("subjectAnswerInfos")
    public ModelAndView subjectAnswerInfos(
            @Param("pageNum") Integer pageNum,
            @RequestParam("questionId") Integer questionId
    ) {
        return new ModelAndView("subjectAnswerInfos", questionnaireService.subjectAnswerInfos(pageNum, questionId));
    }
}
