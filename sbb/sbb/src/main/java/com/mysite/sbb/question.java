package com.mysite.sbb;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class question {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 200)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String contents;

    private LocalDateTime createDate;

    // CascadeType.REMOVE : 질문을 삭제하면 그에 달린 답변들도 모두 함께 삭제하기 위해
    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<answer> answerList;

}
