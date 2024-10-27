package com.example.use_mustache.repository;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import com.example.use_mustache.entity.Article;

public interface ArticleRepository extends CrudRepository<Article, Long> {
    @Override
    ArrayList<Article> findAll();
}
