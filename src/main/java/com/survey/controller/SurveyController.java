package com.survey.controller;

import com.survey.entity.Question;
import com.survey.model.SelectedQuestionAnswer;
import com.survey.model.QuestionAnswers;
import com.survey.service.SurveyService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/survey")
@Api(value = "/survey", tags = "Questions Resources")
public class SurveyController {
    final String QUESTIONS = "/questions";
    final String SUBMIT_QUESTIONS = "/submitQuestions";
    final String QUESTIONS_BY_ID = "/questions/{questionId}";
    @Autowired
    private SurveyService service;

    @PostMapping(value = QUESTIONS,consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Save New Questions", notes = "Return Saved Questions", response = Question.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message="Questions Created"),
            @ApiResponse(code = 404, message="Questions not found"),
            @ApiResponse(code = 500, message="Internal Server Error")
    })
    public ResponseEntity<QuestionAnswers> saveQuestionWithAnswer(@RequestBody QuestionAnswers questionAnswers) {
        QuestionAnswers aqRes = service.saveQuestion(questionAnswers);
        return new ResponseEntity<>(aqRes,HttpStatus.CREATED);
    }
    @GetMapping(value = QUESTIONS)
    @ApiOperation(code = 200,value = "Find All Questions",
            notes = "Return All Questions Details", response = Question.class,responseContainer = "List")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message="All Questions"),
        @ApiResponse(code = 404, message="No Questions found"),
        @ApiResponse(code = 500, message="Internal Server Error")
    })
    public ResponseEntity<List<QuestionAnswers>> getQuestions() {
         List<QuestionAnswers> questionAnswrs = service.getAllQuestions();
         return new ResponseEntity<>(questionAnswrs,HttpStatus.OK);
    }

    @GetMapping(value = QUESTIONS_BY_ID)
    @ApiOperation(code = 200, value = "Find Particular Questions",
            notes = "Return Questions Details by Id", response = Question.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message="Particular Questions"),
        @ApiResponse(code = 404, message="Questions not found"),
        @ApiResponse(code = 500, message="Internal Server Error")
    })
    public ResponseEntity<QuestionAnswers> getQuestionsById(
            @ApiParam(value = "questionId is needed to corresponding question",required = true)
            @PathVariable(name = "questionId") Long questionId) {
        QuestionAnswers aqRes = service.getQuestionById(questionId);
        return new ResponseEntity<>(aqRes,HttpStatus.OK);
    }

    @DeleteMapping(value = QUESTIONS_BY_ID)
    @ApiOperation(code = 200,value = "Delete Questions", notes = "Delete Question By Id", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="Questions Deleted"),
            @ApiResponse(code = 204, message="No Content"),
            @ApiResponse(code = 404, message="Questions not found"),
            @ApiResponse(code = 500, message="Internal Server Error")
    })
    public String deleteQuestions(@PathVariable(name = "questionId") Long questionId) {
        return service.deleteQuestionById(questionId);
    }

    @PostMapping(value = SUBMIT_QUESTIONS,consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Submit Questions With Corresponding Answer",
            notes = "Return Submitted Answers", response = Question.class,responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message="Questions Created"),
            @ApiResponse(code = 404, message="Questions not found"),
            @ApiResponse(code = 500, message="Internal Server Error")
    })
    public ResponseEntity<List<SelectedQuestionAnswer>> submitQuestionsWithAnswer(
            @RequestBody List<SelectedQuestionAnswer> questionAnswers) {
        List<SelectedQuestionAnswer> aqRes = service.submitQuestionsWithAnswer(questionAnswers);
        return new ResponseEntity<>(aqRes,HttpStatus.CREATED);
    }

}
