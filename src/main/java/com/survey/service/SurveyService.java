package com.survey.service;

import com.survey.model.SelectedQuestionAnswer;
import com.survey.model.QuestionAnswers;

import java.util.List;

public interface SurveyService {
    QuestionAnswers getQuestionById(Long questionId);

    List<QuestionAnswers> getAllQuestions();

    String deleteQuestionById(Long questionId);

    QuestionAnswers saveQuestion(QuestionAnswers questionAnswers);

    List<SelectedQuestionAnswer> submitQuestionsWithAnswer(List<SelectedQuestionAnswer> questionAnswers);
}
