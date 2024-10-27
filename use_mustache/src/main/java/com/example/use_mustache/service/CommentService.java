package com.example.use_mustache.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.use_mustache.dto.CommentDto;
import com.example.use_mustache.entity.Comment;
import com.example.use_mustache.repository.ArticleRepository;
import com.example.use_mustache.repository.CommentRepository;

@Service
public class CommentService {
  @Autowired
  private CommentRepository commentRepository;

  @Autowired
  private ArticleRepository articleRepository;  // FK 때문에라도 ArticleRepository의 DI가 필요하다

  public List<CommentDto> getComments(Long article_id) {
    // 댓글 조회
    List<Comment> comments = commentRepository.findByArticleId(article_id);
    List<CommentDto> dtos = new ArrayList<CommentDto>();
    
    for(int i = 0; i < comments.size(); i++){
      Comment c = comments.get(i);  //comments(Comment List)로부터 하나씩 빼서 Comment로 만듦
      CommentDto dto = CommentDto.createCommentDto(c);
      dtos.add(dto);
    }

    
    return null; 
  }


}
