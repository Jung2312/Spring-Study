package com.mysite.sbb.question;

import com.mysite.sbb.question.question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<question, Integer> {
    question findByTitle(String title);
    question findByTitleAndContents(String title, String contents);
    List<question> findByTitleLike(String Title);


}
