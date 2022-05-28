package com.example.wantudy.application.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name="keyword")
public class Keyword {
    @Id
    @Column(name = "keywordId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long keywordId;

    @NotNull
    private String keyword;

    @Builder
    public Keyword(String keyword) {
        this.keyword = keyword;
    }
}

