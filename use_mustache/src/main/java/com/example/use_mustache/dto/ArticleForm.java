package com.example.use_mustache.dto;

import com.example.use_mustache.entity.Article;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor  // Request를 주기 위해서는 DTO에 기본 생성자를 추가해 Jackson이 객체를 생성할 수 있도록 하였다.
@Data
public class ArticleForm {
    private Long id;
    private String title; // 제목을 받을 필드
    private String content; // 내용을 받을 필드

    public Article toEntity() {
        return new Article(id, title, content);
    }
}
