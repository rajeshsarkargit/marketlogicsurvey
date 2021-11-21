package com.survey.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "QUESTION_ANSWER")
@ApiModel(description = "Sample Model for Questions And its Asnwer")
public class QuestionAnswer {
    @Id
    @GeneratedValue
    private Long Id;

    @Column(name="QUESTION_ID")
    @ApiModelProperty(notes = "Sample Question")
    private Long questionId;

    @Column(name="ANSWER_ID")
    @ApiModelProperty(notes = "Corresponding Answer")
    private Long answerId;

    @Column(name="USER_ID")
    @ApiModelProperty(notes = "User")
    private Long userId;

    @CreatedDate
    @Column(name = "CREATED_AT", nullable = false, updatable = false)
    private Date createdAt;

    public QuestionAnswer(Long questionId, Long answerId, Long userId) {
        this.questionId = questionId;
        this.answerId = answerId;
        this.userId = userId;
        this.createdAt = new Date();
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
