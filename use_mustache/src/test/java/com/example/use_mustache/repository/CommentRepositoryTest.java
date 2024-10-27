package com.example.use_mustache.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.use_mustache.entity.Article;
import com.example.use_mustache.entity.Comment;

@DataJpaTest
public class CommentRepositoryTest {    // Repository가 잘 작동할지 확인
  @Autowired
  CommentRepository commentRepository;

  @Test
  @DisplayName ("테스트 : 특정 게시글의 모든 댓글 조회") //테스트의 메서드명 고정
  void testFindByArticleId() {  // Article의 아이디를 통해 comment 데이터 찾기
    /**
     * 입력 데이터 준비(해당 상황은 ArticleId를 이용해 DB를 찾는 상황)
     * 실제 데이터
     * 예상 데이터
     * 비교 및 검증
     */
    Long articleId = 4L;
    
    List<Comment> comments = commentRepository.findByArticleId(articleId);  // Param을 통해 articleId 동기화.

    //예상 데이터 : DB를 확인하면서 내가 원하는 방식으로 하면 어떤 답이 나와야하는지를 확인
    Article article = new Article(4L, "당신의 인생 영화는?", "댓글 고");
    Comment a = new Comment(1L, article, "Park", "굿 윌 헌팅");
    Comment b = new Comment(2L, article, "Kim", "쇼생크 탈출");
    Comment c = new Comment(3L, article, "Lee","뷰티풀 라이프");
    List<Comment> expectComments = Arrays.asList(a,b,c);

    assertEquals(expectComments.toString(), comments.toString(), "4번 글의 모든 댓글 출력");
  }

  @Test
  @DisplayName("테스트 : 특정 인물의 모든 댓글 조회")
  void testFindByNickname() {
    String nickname = "Park";

    // 실제 데이터
    List<Comment> comments = commentRepository.findByNickname(nickname);
    
    // 예상 데이터
    Comment a = new Comment(1L, new Article(4L,"당신의 인생 영화는?", "댓글 고"), nickname,"굿 윌 헌팅");
    Comment b = new Comment(4L, new Article(5L,"당신의 소울 푸드는?", "댓글 고고"), nickname,"치킨");
    Comment c = new Comment(7L, new Article(6L,"당신의 취미는?", "댓글 고고고"), nickname,"잠");
    List<Comment> expect = Arrays.asList(a,b,c);

    assertEquals(comments.toString(), expect.toString(), "특정 인물의 모든 댓글 조회 테스트");

  }
}
