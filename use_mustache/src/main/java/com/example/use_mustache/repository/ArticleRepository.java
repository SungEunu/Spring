package com.example.use_mustache.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.use_mustache.entity.Article;

import java.util.ArrayList;

public interface ArticleRepository extends CrudRepository<Article, Long> {
    
    @Override
    ArrayList<Article> findAll();
}
