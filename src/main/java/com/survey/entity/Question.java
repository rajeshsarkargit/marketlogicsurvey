package com.survey.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "QUESTIONS")
@ApiModel(description = "Sample Model for Questions")
public class Question {
    @Id
    @GeneratedValue
    private Long questionId;

    @Column(name="QUESTION")
    @ApiModelProperty(notes = "Sample Question")
    private String question;

    @CreatedDate
    @Column(name = "CREATED_AT", nullable = false, updatable = false)
    private Date createdAt;

    public Question(){}
    public Question(Long questionId, String description) {
        this.questionId = questionId;
        this.question = description;
        this.createdAt = new Date();
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
