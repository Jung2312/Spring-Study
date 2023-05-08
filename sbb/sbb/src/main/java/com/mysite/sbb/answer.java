package com.mysite.sbb;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Entity
public class answer {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "TEXT")
    private String contents;

    private LocalDateTime createDate;

    @ManyToOne// 부모 자식 관계 구조 q - 부모, a - 자식
    @JoinColumn(name="questionId")
    @JsonBackReference
    private question question;
}
