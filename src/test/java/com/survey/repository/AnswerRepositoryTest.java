
package com.survey.repository;

import com.survey.entity.Answer;
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
public class AnswerRepositoryTest {
    @Autowired
    AnswerRepository repository;

    @Test
    public void a_create(){
        Answer Answer = new Answer(null ,"Answer-1",1l);
        Answer Answer1 = repository.save(Answer);
        assertNotNull(Answer1.getAnswerId());
    }
    @Test
    public void b_findAll(){
        List<Answer> answers = repository.findAll();
        assertNotNull(answers);
    }
    @Test
    public void c_findOne(){
        Answer answer = repository.findOne(1L);
        assertNotNull(answer);
    }
    @Test
    public void d_delete(){
        Answer answer = repository.findOne(1L);
        if(null != answer){
            repository.delete(1L);
        }
        Answer answer1 = repository.findOne(1L);
        assertNull(answer1);
    }
}


