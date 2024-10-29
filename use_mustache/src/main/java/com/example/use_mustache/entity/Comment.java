package com.example.use_mustache.entity;

import com.example.use_mustache.dto.CommentDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor

// Comment도 Article과 동일하게 작동을 하기는 한다. 하지만 Comment와 Article의 DB가 연동되어야 하기 때문에 DB 구조를 담당하는 Entity에서 Article을 참조해주는 것이다.
public class Comment {

  @Id // 대표키 지정
  @GeneratedValue(strategy = GenerationType.IDENTITY) // DB가 자동으로 1씩 증가
  private Long id;
  
  @ManyToOne  // article과 comment가 n:1 관계라는 것을 보여준다.
  @JoinColumn(name="article_id")  // 해당 키가 외래키로 Article entity의 기본키와 매핑된다.
  private Article article;  // Article과 연결시켜주는 역할. Article과 Comment는 1 : n 관계를 유지할 것이다.
  
  @Column
  private String nickname;
  
  @Column
  private String body;

  public static Comment createComment(CommentDto dto, Article article) {
    if(dto.getId() != null){
      throw new IllegalArgumentException("댓글 생성 실패! 댓글의 id 오류가 발생");
    }
    if(dto.getArticle_id() != article.getId()){
      throw new IllegalArgumentException("댓글 생성 실패! 게시글의 id 오류");
    }
    
    return new Comment(
      dto.getId(),
      article,
      dto.getNickname(),
      dto.getBody()
    );
  }

}
