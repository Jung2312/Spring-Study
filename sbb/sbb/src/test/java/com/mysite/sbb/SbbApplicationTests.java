package com.mysite.sbb;

import com.mysite.sbb.answer.AnswerRepository;
import com.mysite.sbb.answer.answer;
import com.mysite.sbb.question.QuestionRepository;
import com.mysite.sbb.question.question;
import org.springframework.transaction.annotation.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;
import java.util.List;

@SpringBootTest
class SbbApplicationTests {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    /*
    @Test
    void testJpa(){
        question q1 = new question();
        q1.setTitle("sbb가 무엇인가요?");
        q1.setContents("sbb에 대해서 알고 싶습니다.");
        q1.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(q1);  // 첫번째 질문 저장

        question q2 = new question();
        q2.setTitle("스프링부트 모델 질문입니다.");
        q2.setContents("id는 자동으로 생성되나요?");
        q2.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(q2);  // 두번째 질문 저장
    }*/

    @Transactional
    @Test
    void testfindAll() {
        List<question> all = this.questionRepository.findAll();
        assertEquals(2, all.size());

        question q = all.get(1);
        assertEquals("스프링부트 모델 질문입니다.", q.getTitle());
    }
    @Transactional
    @Test
    void testfindById() {
        Optional<question> oq = this.questionRepository.findById(2);
        if(oq.isPresent()) {
            question q = oq.get();
            assertEquals("스프링부트 모델 질문입니다.", q.getTitle());
        }
    }
    @Transactional
    @Test
    void testfindByTitle() {
        question q = this.questionRepository.findByTitle("스프링부트 모델 질문입니다.");;
        assertEquals(2, q.getId());
    }
    @Transactional
    @Test
    void testfindByTitleAndContents() {
        question q = this.questionRepository.findByTitleAndContents(
                "스프링부트 모델 질문입니다.", "id는 자동으로 생성되나요?");
        assertEquals(2, q.getId());
    }
    @Transactional
    @Test
    void testfindByTitleLike() {
        List<question> qList = this.questionRepository.findByTitleLike("스프링%");
        question q = qList.get(0);
        assertEquals("스프링부트 모델 질문입니다.", q.getTitle());
    }
    @Transactional
    @Test
    void testModiTitle() {
        Optional<question> oq = this.questionRepository.findById(1);
        assertTrue(oq.isPresent());
        question q = oq.get();
        q.setTitle("수정된 제목");
        this.questionRepository.save(q);
    }

    @Transactional
    @Test
    void testDel() {
        assertEquals(2, this.questionRepository.count());
        Optional<question> oq = this.questionRepository.findById(1);
        assertTrue(oq.isPresent());
        question q = oq.get();
        this.questionRepository.delete(q);
        assertEquals(1, this.questionRepository.count());
    }

    @Transactional
    @Test
    void testAnswerAdd() {
        Optional<question> oq = this.questionRepository.findById(2);
        assertTrue(oq.isPresent());
        question q = oq.get();

        answer a = new answer();
        a.setContents("네 자동으로 생성됩니다.");
        a.setQuestion(q);  // 어떤 질문의 답변인지 알기위해서 Question 객체가 필요하다.
        a.setCreateDate(LocalDateTime.now());
        this.answerRepository.save(a);
    }

    @Transactional
    @Test
    void testSearch() {
        Optional<answer> oa = this.answerRepository.findById(1);
        assertTrue(oa.isPresent());
        answer a = oa.get();
        assertEquals(2, a.getQuestion().getId());
    }

    @Transactional
    @Test
    void testA_Qconnect() {
        Optional<question> oq = this.questionRepository.findById(2);
        assertTrue(oq.isPresent());
        question q = oq.get();

        List<answer> answerList = q.getAnswerList();

        assertEquals(1, answerList.size());
        assertEquals("네 자동으로 생성됩니다.", answerList.get(0).getContents());
    }
}

