package com.survey.service;

import com.survey.entity.Answer;
import com.survey.entity.Question;
import com.survey.model.QuestionAnswers;
import com.survey.repository.AnswerRepository;
import com.survey.repository.QuestionRepository;
import com.survey.util.Stubs;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ServiceTest {

    @Mock
    QuestionRepository questionRepository;
    @Mock
    AnswerRepository answerRepository;

    @InjectMocks
    SurveyServiceImpl service;

    @Before
    public void init(){
    	MockitoAnnotations.initMocks(this);
        Mockito.when(questionRepository.findOne(Mockito.any(Long.class))).thenReturn(Stubs.getQuestion());
        Mockito.when(answerRepository.findByQuestionId(Mockito.any(Long.class))).thenReturn(Stubs.getListOfAnswers());
        Mockito.when(questionRepository.save(Mockito.any(Question.class))).thenReturn(Stubs.getQuestion());
        Mockito.when(answerRepository.save(Mockito.any(Answer.class))).thenReturn(Stubs.getAnswer());
        Mockito.doNothing().when(questionRepository).delete(Mockito.any(Question.class));
        Mockito.doNothing().when(answerRepository).delete(Mockito.any(Answer.class));
    }
    @Test
    public void saveQuestion() throws Exception{
        QuestionAnswers questionAnswers = service.saveQuestion(Stubs.getQuestionAnswers());
        assertNotNull(questionAnswers);
    }
    @Test
    public void getQuestionById() throws Exception{
        QuestionAnswers questionAnswers = service.getQuestionById(1l);
    	assertNotNull(questionAnswers);
    }
    @Test
    public void getAllQuestions() throws Exception{
    	List<QuestionAnswers> questionAnswers = service.getAllQuestions();
    	assertNotNull(questionAnswers);
    }

    @Test
    public void deleteQuestionById() throws Exception{
    	String deleted = service.deleteQuestionById(1l);
    	assertNotNull(deleted);
    	assertEquals(deleted,"DELETED");
    }

}

