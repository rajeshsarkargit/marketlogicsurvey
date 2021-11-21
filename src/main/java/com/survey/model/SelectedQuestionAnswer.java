package com.survey.model;

import com.survey.entity.Answer;
import com.survey.entity.Question;

import java.util.List;

public class SelectedQuestionAnswer {
    Question question;
    Answer answer;

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }
}
