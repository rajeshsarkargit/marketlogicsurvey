package com.survey.controller;

import com.survey.model.QuestionAnswers;
import com.survey.model.SelectedQuestionAnswer;
import com.survey.service.SurveyServiceImpl;
import com.survey.util.Stubs;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static com.survey.util.Util.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    SurveyServiceImpl service;

    @InjectMocks
    SurveyController controller;

    final String GET_QUESTION = "/survey/questions";
    final String GET_QUESTION_BY_ID = "/survey/questions/{questionId}";
    final String POST_QUESTION = "/survey/questions";
    final String DELETE_QUESTION_BY_ID = "/survey/questions/{questionId}";
    final String SUBMIT_QUESTIONS = "/survey/submitQuestions";

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        when(service.saveQuestion(Mockito.any(QuestionAnswers.class))).thenReturn(Stubs.getQuestionAnswers());
        when(service.getQuestionById(Mockito.any(Long.class))).thenReturn(Stubs.getQuestionAnswers());
        when(service.getAllQuestions()).thenReturn(Stubs.getListQuestionAnswers());
        when(service.deleteQuestionById(Mockito.any(Long.class))).thenReturn("DELETED");
        when(service.submitQuestionsWithAnswer(Mockito.any())).thenReturn(Stubs.getSelectedQuestionAnswer());
    }

    @Test
    public void saveQuestion() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .post(POST_QUESTION)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(Stubs.getQuestionAnswers()))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();
        String content = result.getResponse().getContentAsString();
        QuestionAnswers qa = stringToObj(content);
        assertNotNull(qa);
        assertNotNull(qa.getQuestion());
        assertNotNull(qa.getAnswers());
    }

    @Test
    public void getAllQuestions() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .get(GET_QUESTION)
                .accept(MediaType.APPLICATION_JSON))
                //.andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        String content = result.getResponse().getContentAsString();
        List<QuestionAnswers> questionAnswers = stringToObjList(content);
        assertNotNull(questionAnswers);
        assertTrue(questionAnswers.size() > 0);
    }

    @Test
    public void getQuestionsById() throws Exception {
        MvcResult result =mockMvc.perform(MockMvcRequestBuilders
                .get(GET_QUESTION_BY_ID, 1)
                .accept(MediaType.APPLICATION_JSON))
                //.andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        String content = result.getResponse().getContentAsString();
        QuestionAnswers questionAnswers = stringToObj(content);
        assertNotNull(questionAnswers);
    }

    @Test
    public void deleteProductsByIdAPI() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .delete(DELETE_QUESTION_BY_ID, 1)
                .accept(MediaType.APPLICATION_JSON))
                //.andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        String content = result.getResponse().getContentAsString();
        assertFalse(content.isEmpty());
        assertEquals(content, "DELETED");
    }
    @Test
    public void submitQuestionsWithAnswer() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .post(SUBMIT_QUESTIONS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonStrFrmList(Stubs.getSelectedQuestionAnswer()))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();
        String content = result.getResponse().getContentAsString();
        List<SelectedQuestionAnswer> answerList = convert_SQA_Str_To_Obj(content);
        assertNotNull(answerList);
        assertTrue(answerList.size() > 0);
    }
}

