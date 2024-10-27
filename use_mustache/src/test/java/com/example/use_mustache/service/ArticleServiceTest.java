package com.example.use_mustache.service;

import org.junit.jupiter.api.Test;  // Test 패키지 임포트
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.use_mustache.dto.ArticleForm;
import com.example.use_mustache.entity.Article;

import static org.junit.jupiter.api.Assertions.*; // 앞으로 사용할 가능성이 있는 패키지 미리 임포트 (*는 해당 메서드 내의 모든 메서드를 임포트한다는 뜻이다.)

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 테스트 과정 - 예상 데이터를 작성하고 실제 데이터를 획득한 후 예상 데이터와 실제 데이터를 비교해 검증하기
 * 즉, 실제 데이터의 인자값을 변수로 정의하고, 예상되는 데이터 결과값과의 비교를 통해 Test를 하는 것이다.
 */
@SpringBootTest   // 해당 클래스를 스프링 부트와 연동해서 테스트한다는 의미이다.
public class ArticleServiceTest {
  @Autowired
  ArticleService articleService;  // articleService 객체를 주입해주어서 ServiceTest를 할 수 있게 하였다.

  @Test
  @Transactional  // 다른 Test에 영향이 갈만한 테스트는 해당 어노테이션 필요
  void create_성공(){
    String title = "이이이이";
    String content = "4444";
    ArticleForm form = new ArticleForm(null, title,content);
    
    // 예상 데이터
    Article expeced = new Article(4L, "이이이이", "4444");

    // 실제 데이터
    Article article = articleService.create(form);

    assertEquals(expeced.toString(), article.toString());
  }

  @Test
  void show_성공_존재하는_id_입력(){
    Long id = 1L;
    //예상 데이터
    Article expectArticle = new Article(id, "11", "aa");

    //실제 데이터
    Article article = articleService.show(id);

    //예상 데이터와 실제 데이터 비교
    assertEquals(expectArticle.toString(), article.toString());
  }

  @Test
  void show_실패(){
    Long id = -1L;
    
    //예상 데이터
    Article expectArticle = null;

    //실제 데이터
    Article article = articleService.show(id);

    //예상 데이터와 실제 데이터 비교
    assertEquals(expectArticle, article);
  }

  

  @Test
  @Transactional
  void create_실패(){
    String title = "이이이이";
    String content = "4444";
    ArticleForm form = new ArticleForm(4L, title,content);
    // ArticleForm에 id를 주면 entity에서 GeneratedValue가 불가능해져서 null로 설정

    // 예상 데이터
    Article expeced = null;

    // 실제 데이터
    Article article = articleService.create(form);

    assertEquals(expeced, article);
  }

  @Test // 해당 메서드가 테스트 코드임을 선언
  void testIndex() {
    // 예상 데이터
    Article a = new Article(1L, "11", "aa");
    Article b = new Article(2L, "22", "bb");
    Article c = new Article(3L, "33", "cc");
    List<Article> expectArticles = new ArrayList<Article>(Arrays.asList(a,b,c));
    
    // 실제 데이터
    List<Article> articles = articleService.index();

    // 예상 데이터와 실제 데이터 비교 (with JUit's assertEquals(x,y) method) 이때 x와 y는 예상 데이터를 문자열로 변환해서 비교해야 한다.
    assertEquals(expectArticles.toString(), articles.toString());
  }
  
  @Test
  @Transactional
  void update_성공_존재하는_id와_title_content가_있는_dto_입력(){
    Long id = 1L;
    String title="가가";
    String content = "나나";
    ArticleForm dto = new ArticleForm(id, title, content);

    // 예상 데이터
    Article expectArticle = new Article(id, title, content);

    // 실제 데이터
    Article article = articleService.update(id, dto);

    //비교
    assertEquals(expectArticle.toString(), article.toString());
  }

  @Test
  @Transactional
  void update_성공_존재하는_id와_title만_있는_dto_입력(){
    Long id = 1L;
    String title = "가가";
    String content = null;
    ArticleForm dto = new ArticleForm(id, title,content);

    // 예상 데이터
    Article expectArticle = new Article(id,title, "aa");

    // 실제 데이터
    Article article = articleService.update(id, dto);

    //비교
    assertEquals(expectArticle.toString(), article.toString());
  }

  @Test
  @Transactional
  void update_실패_존재하지_않는_id의_dto_입력(){
    Long id = -1L;
    String title = "가가";
    String content = "나나";
    ArticleForm dto = new ArticleForm(id, title,content);

    // 예상 데이터
    Article expectArticle = null;
    
    // 실제 데이터
    Article article = articleService.update(id, dto);

    //비교
    assertEquals(expectArticle, article);
  }

  @Test
  @Transactional
  void delete_성공_존재하는_id_입력(){
    Long id = 1L;
    
    // 예상 데이터
    Article expectArticle = new Article(1L, "11", "aa");

    // 실제 데이터
    Article article = articleService.delete(id);

    assertEquals(expectArticle.toString(), article.toString());
  }

  @Test
  @Transactional
  void delete_실패_존재하지_않는_id_입력(){
    Long id = -1L;
    
    // 예상 데이터
    Article expectArticle = null;
    
    // 실제 데이터
    Article article = articleService.delete(id);

    assertEquals(expectArticle, article);
  }
}
