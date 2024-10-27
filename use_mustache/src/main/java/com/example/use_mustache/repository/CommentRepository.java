package com.example.use_mustache.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.use_mustache.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
  // JpaRepository는 CRUD 기능뿐만 아니라 Paging&Sorting 기능도 같이 있다.  
  // 조회 방법 - 쿼리를 통해서 가능. (네이티브 쿼리 메서드, @Query or orm.xml)


  // 특정 게시글의 모든 댓글 조회
  //@Query를 사용한 네이티브 쿼리 메서드 구현
  @Query(value="SELECT * FROM comment WHERE article_id = :articleId", nativeQuery = true)
  List<Comment> findByArticleId(@Param("articleId") Long articleId); 
  // 현재 해당 매개변수를 사용할 때 매개변수의 이름이 제공되지 않으면 에러가 발생한다. 따라서 @Param이 필요하다.
  // 위의 Query문과 해당 articleId를 매핑시키기 위해서 사용하는 것이다.

  
  // 특정 닉네임의 모든 댓글 조회
  List<Comment> findByNickname(String nickname);
}