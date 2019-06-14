package com.sun.controller;

import com.sun.service.AccountService;
import com.sun.service.QuestionnaireService;
import com.sun.utils.JsonUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class AdminController {

    @Resource
    private AccountService accountService;
    @Resource
    private QuestionnaireService questionnaireService;

    @GetMapping("/admin")
    public ModelAndView admin() {
        return new ModelAndView("admin");
    }

    @GetMapping("/accountInfos")
    public ModelAndView accountInfos(@Param("pageNum") Integer pageNum) {
        return new ModelAndView("accountInfos", accountService.accountInfos(pageNum));
    }

    @GetMapping("/questionnaireInfos2")
    public ModelAndView questionnaireInfos(@Param("pageNum") Integer pageNum) {
        return questionnaireService.questionnaireInfos2(pageNum);
    }

    @GetMapping("/banItem")
    public ModelAndView banItem(
            @Param("questionnaireId") Integer questionnaireId,
            @Param("accountId") Integer accountId
    ) {
        return new ModelAndView("alert", questionnaireService.banItem(questionnaireId, accountId));
    }

    @GetMapping("/editQuestionnaire2")
    public ModelAndView editQuestionnaire2(
            @Param("questionnaireId") Integer questionnaireId,
            HttpSession session
    ) {
        return new ModelAndView("editQuestionnaire2", questionnaireService.editQuestionnaire(questionnaireId, session));
    }
}
