package com.survey.util;

import com.survey.entity.Answer;
import com.survey.entity.Question;
import com.survey.model.QuestionAnswers;
import com.survey.model.SelectedQuestionAnswer;

import java.util.ArrayList;
import java.util.List;

public class Stubs {
    public static Question getQuestion(){
        Question question = new Question(1l ,"Questions-1");
        return question;
    }
    public static Answer getAnswer(){
        Answer answer = new Answer(1l ,"Answer-1",1l);
        return answer;
    }
    public static List<Answer> getListOfAnswers(){
        List<Answer> answers = new ArrayList<>();
        answers.add(getAnswer());
        return answers;
    }
    public static QuestionAnswers getQuestionAnswers(){
        QuestionAnswers questionAnswers = new QuestionAnswers();
        List<Answer> answers = new ArrayList<>();
        answers.add(getAnswer());
        questionAnswers.setQuestion(getQuestion());
        questionAnswers.setAnswers(answers);
        return questionAnswers;
    }
    public static List<QuestionAnswers> getListQuestionAnswers(){
        List<QuestionAnswers> questionAnswers = new ArrayList<>();
        questionAnswers.add(getQuestionAnswers());
        return questionAnswers;
    }
    public static List<SelectedQuestionAnswer> getSelectedQuestionAnswer(){
        List<SelectedQuestionAnswer> answerList = new ArrayList<>();
        SelectedQuestionAnswer selectedQuestionAnswer = new SelectedQuestionAnswer();
        selectedQuestionAnswer.setQuestion(getQuestion());
        selectedQuestionAnswer.setAnswer(getAnswer());
        answerList.add(selectedQuestionAnswer);
        return answerList;
    }
}
