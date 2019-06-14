package com.sun.controller.dto;

import com.sun.entity.Question;
import com.sun.entity.Questionnaire;

import java.util.List;

public class RequestDto {
    private Questionnaire questionnaire;
    private Question question;
    private List<Question> questions;

    public Questionnaire getQuestionnaire() {
        return questionnaire;
    }

    public void setQuestionnaire(Questionnaire questionnaire) {
        this.questionnaire = questionnaire;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
