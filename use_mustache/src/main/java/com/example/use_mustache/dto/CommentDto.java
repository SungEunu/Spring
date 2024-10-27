package com.example.use_mustache.dto;

import com.example.use_mustache.entity.Comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CommentDto {
  private Long id;
  private Long article_id;
  private String nickname;
  private String body;
  
  
  public static CommentDto createCommentDto(Comment comment) {
    return new CommentDto(
      comment.getId(),
      comment.getArticle().getId(),
      comment.getNickname(),
      comment.getBody()
    );
  }

}
