package com.example.use_mustache.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.use_mustache.dto.CommentDto;
import com.example.use_mustache.repository.ArticleRepository;
import com.example.use_mustache.repository.CommentRepository;

@Service
public class CommentService {
  @Autowired
  private CommentRepository commentRepository;

  @Autowired
  private ArticleRepository articleRepository;  // FK 때문에라도 ArticleRepository의 DI가 필요하다

  // 댓글 조회
  public List<CommentDto> check(Long article_id) {
    
    // List<Comment> comments = commentRepository.findByArticleId(article_id);
    // List<CommentDto> dtos = new ArrayList<>();
    
    // for(int i = 0; i < comments.size(); i++){
    //   Comment c = comments.get(i);  //comments(Comment List)로부터 하나씩 빼서 Comment로 만듦
    //   CommentDto dto = CommentDto.createCommentDto(c);
    //   dtos.add(dto);
    // }

    
    return commentRepository.findByArticleId(article_id)
    .stream()
    .map(comment -> CommentDto.createCommentDto(comment))
    .collect(Collectors.toList()); 
  }


}
