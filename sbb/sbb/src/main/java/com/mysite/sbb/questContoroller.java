/*
package com.mysite.sbb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Controller
@RequestMapping(path = "/quest")
public class questContoroller {
    @Autowired
    private QuestionRepository questionRepository;

    @PostMapping(path = "/add")
    public String addNewQuestion(String subject, String content){
        question q = new question();
        q.setTitle(subject);
        q.setContents(content);
        q.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(q);
        return "redirect:/";
    }
}
*/
