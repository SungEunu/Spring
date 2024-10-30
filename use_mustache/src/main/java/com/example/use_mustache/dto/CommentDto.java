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
  private Long article_id;  // 만약 Entity와 다르게 받는다면 JsonProperty를 통해 만들면 된다.
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
