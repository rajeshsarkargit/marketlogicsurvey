package com.survey.service;

import com.survey.entity.QuestionAnswer;
import com.survey.exception.BusinessException;
import com.survey.entity.Answer;
import com.survey.entity.Question;
import com.survey.model.SelectedQuestionAnswer;
import com.survey.model.QuestionAnswers;
import com.survey.repository.AnswerRepository;
import com.survey.repository.QuestionAnswerRepository;
import com.survey.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SurveyServiceImpl implements SurveyService {

    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private QuestionAnswerRepository questionAnswerRepository;

    @Override
    public QuestionAnswers saveQuestion(QuestionAnswers questionAnswers) {
        Question question = questionAnswers.getQuestion();
        if(StringUtils.isEmpty(question) || StringUtils.isEmpty(question.getQuestion())){
            throw new BusinessException("Question should not be empty");
        }
        List<Answer> answers = questionAnswers.getAnswers();
        if(null == answers || answers.size()==0){
            throw new BusinessException("Question should have answers");
        }else if(answers.size()>4){
            throw new BusinessException("Maximum 4 options are allowed");
        }
        question.setCreatedAt(new Date());
        question = questionRepository.save(question);

        for(Answer answer : answers){
            answer.setQuestionId(question.getQuestionId());
            answer.setCreatedAt(new Date());
            answerRepository.save(answer);
        }
        return questionAnswers;
    }

    @Override
    public QuestionAnswers getQuestionById(Long questionId) {
        QuestionAnswers questionAnswers = new QuestionAnswers();
        Question question = questionRepository.findOne(questionId);
        if(null == question){
            throw new BusinessException("Question does not exists");
        }
        List<Answer> answers = answerRepository.findByQuestionId(question.getQuestionId());
        questionAnswers.setQuestion(question);
        questionAnswers.setAnswers(answers);
        return questionAnswers;
    }

    @Override
    public List<QuestionAnswers> getAllQuestions() {
        List<QuestionAnswers> lisOfQuestionAnswers = new ArrayList<>();
        List<Question> questions = questionRepository.findAll();
        for(Question question : questions){
            QuestionAnswers questionAnswers = new QuestionAnswers();
            questionAnswers.setQuestion(question);
            List<Answer> answers = answerRepository.findByQuestionId(question.getQuestionId());
            if(null == answers || answers.size()==0){
                throw new BusinessException("Question does have any answers");
            }
            questionAnswers.setAnswers(answers);
            lisOfQuestionAnswers.add(questionAnswers);
        }
        return lisOfQuestionAnswers;
    }

    @Override
    public String deleteQuestionById(Long questionId) {

        List<Answer> answers = answerRepository.findByQuestionId(questionId);
        if(null == answers || answers.size()==0){
            throw new BusinessException("Question does have any answers");
        }
        questionRepository.delete(questionId);
        for(Answer answer : answers) {
            answerRepository.delete(answer);
        }
         return "DELETED";
    }

    @Override
    public List<SelectedQuestionAnswer> submitQuestionsWithAnswer(List<SelectedQuestionAnswer> questionAnswersList) {
        for(SelectedQuestionAnswer questionAnswers : questionAnswersList) {
            Question question = questionAnswers.getQuestion();
            Answer answer = questionAnswers.getAnswer();
            if (null == question || null == question.getQuestionId()) {
                throw new BusinessException("Question Needs to be Select");
            }else {
                question = questionRepository.getOne(question.getQuestionId());
                if (null == question){
                    throw new BusinessException("Question No Longer exists");
                }
            }
            if (null == answer) {
                throw new BusinessException("Question should have one selected answer");
            }
            QuestionAnswer questionAnswer = new QuestionAnswer(question.getQuestionId(),answer.getAnswerId(),1l);
            questionAnswerRepository.save(questionAnswer);
        }
        return questionAnswersList;
    }
}
