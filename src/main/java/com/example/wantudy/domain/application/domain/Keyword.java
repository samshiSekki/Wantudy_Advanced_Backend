package com.example.wantudy.domain.application.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="keyword")
public class Keyword {
    @Id
    @Column(name = "keywordId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long keywordId;

    @NotNull
    private String keyword;

    @OneToMany(
            mappedBy = "keyword",
            targetEntity = ApplicationKeyword.class,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonManagedReference
    private List<ApplicationKeyword> applicationKeywords = new ArrayList<>();

    @Builder
    public Keyword(String keyword) {
        this.keyword = keyword;
    }
}

