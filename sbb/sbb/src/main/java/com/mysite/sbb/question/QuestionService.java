package com.mysite.sbb.question;

import java.util.List;
import java.util.Optional;
import com.mysite.sbb.DataNotFoundException;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public List<question> getList() {
        return this.questionRepository.findAll();
    }

    public question getQuestion(Integer id) {
        Optional<question> question = this.questionRepository.findById(id);
        if (question.isPresent()) {
            return question.get();
        } else {
            throw new DataNotFoundException("question not found");
        }
    }

    public void create(String title, String contents) {
        question q = new question();
        q.setTitle(title);
        q.setContents(contents);
        q.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(q);
    }
}