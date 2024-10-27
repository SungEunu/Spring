package com.example.use_mustache.service;

import org.junit.jupiter.api.Test;  // Test 패키지 임포트
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.use_mustache.entity.Article;

import static org.junit.jupiter.api.Assertions.*; // 앞으로 사용할 가능성이 있는 패키지 미리 임포트 (*는 해당 메서드 내의 모든 메서드를 임포트한다는 뜻이다.)

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 테스트 과정 - 예상 데이터를 작성하고 실제 데이터를 획득한 후 예상 데이터와 실제 데이터를 비교해 검증하기
 */
@SpringBootTest   // 해당 클래스를 스프링 부트와 연동해서 테스트한다는 의미이다.
public class ArticleServiceTest {
  @Autowired
  ArticleService articleService;  // articleService 객체를 주입해주어서 ServiceTest를 할 수 있게 하였다.

  @Test // 해당 메서드가 테스트 코드임을 선언
  void testIndex() {
    // 예상 데이터
    Article a = new Article(1L, "11", "aa");
    Article b = new Article(2L, "22", "bb");
    Article c = new Article(4L, "33", "cc");
    List<Article> expectArticles = new ArrayList<Article>(Arrays.asList(a,b,c));
    
    // 실제 데이터
    List<Article> articles = articleService.index();

    // 예상 데이터와 실제 데이터 비교 (with JUit's assertEquals(x,y) method) 이때 x와 y는 예상 데이터를 문자열로 변환해서 비교해야 한다.
    assertEquals(expectArticles.toString(), articles.toString());
  }
}
