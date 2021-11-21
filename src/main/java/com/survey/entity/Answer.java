package com.survey.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "ANSWERS")
@ApiModel(description = "Sample Model for Answers")
public class Answer {
    @Id
    @GeneratedValue
    private Long answerId;

    @Column(name="ANSWER")
    @ApiModelProperty(notes = "Sample Answer")
    private String answer;

    @Column(name="QUESTION_ID")
    @ApiModelProperty(notes = "Corresponding Question")
    private Long questionId;

    @CreatedDate
    @Column(name = "CREATED_AT", nullable = false, updatable = false)
    private Date createdAt;

    public Answer(){}
    public Answer(Long answerId, String description,Long questionId) {
        this.answerId = answerId;
        this.answer = description;
        this.questionId = questionId;
        this.createdAt = new Date();
    }

    public Long getAnswerId() {
        return answerId;
    }


    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

}
