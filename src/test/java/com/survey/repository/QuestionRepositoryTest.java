
package com.survey.repository;

import com.survey.entity.Question;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@DataJpaTest
@Rollback(false)
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class QuestionRepositoryTest {
    @Autowired
    QuestionRepository repository;

    @Test
    public void a_create(){
        Question question = new Question(null ,"Question-1");
        Question question1 = repository.save(question);
        assertNotNull(question1.getQuestionId());
    }
    @Test
    public void b_findAll(){
        List<Question> questions = repository.findAll();
        assertNotNull(questions);
    }
    @Test
    public void c_findOne(){
        Question question1 = repository.findOne(1L);
        assertNotNull(question1);
    }
    @Test
    public void d_delete(){
        Question question = repository.findOne(1L);
        if(null != question){
            repository.delete(1L);
        }
        Question question1 = repository.findOne(1L);
        assertNull(question1);
    }
}


