package com.example.use_mustache.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.use_mustache.dto.ArticleForm;
import com.example.use_mustache.entity.Article;
import com.example.use_mustache.repository.ArticleRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service  //서비스 객체 생성 - 해당 객체는 client로부터 http를 전송받는 REST controller와 DB와 연결되는 repository를 이어주는 역할을 한다.
public class ArticleService {
  @Autowired
  private ArticleRepository articleRepository;  // Autowired를 통해서 객체를 주입해 Repository와 controller를 둘 다 연결시켰다.

  public List<Article> index(){
    List<Article> articles = articleRepository.findAll();
    return articles;
  }
  
  public Article show(Long id){
    Article article = articleRepository.findById(id).orElse(null);
    return article;
  }

  public Article create(ArticleForm dto){
    log.info(dto.toString());
    Article article= dto.toEntity();
    if(article.getId() != null){
      return null;
    }
    return articleRepository.save(article);
  }

  public Article update(Long id,ArticleForm dto){
    Article article = dto.toEntity();
    
    Article updateArticle = articleRepository.findById(id).orElse(null);
    
    if(updateArticle == null || id != article.getId()){
      log.info("잘못된 요청!");
      return null;
    }

    updateArticle.patch(article); // 기존 json에 patch라는 메서드를 이용해 정보 업데이트
    Article update = articleRepository.save(updateArticle);

    return update;
  }

  public Article delete(Long id){
    Article deleArticle = articleRepository.findById(id).orElse(null);

    if(deleArticle == null){
      return null;
    }
    articleRepository.delete(deleArticle);
    return deleArticle;
  }

  @Transactional
  public List<Article> createArticles(List<ArticleForm> dtos) {
    // dtos를 하나씩 불러서 entity로 만들어주기. List니까 collect 메서드 사용
    List<Article> articleList = dtos.stream()
                                    .map(dto->dto.toEntity())
                                    .collect(Collectors.toList());

    // 엔티티를 DB에 저장하기
    articleList.stream()
               .forEach(article -> articleRepository.save(article));
               

    //강제 예외 발생 (찾는 id값이 존재할 수 없는 -1인 경우)
    articleRepository.findById(-1L)
                     .orElseThrow(()-> new IllegalArgumentException("결제 실패!"));
    /**
     * 일단 받은 객체에 대해 json으로 data를 db에 저장한 후 강제 에러가 발생해서 rollback되어 저장이 안되는 상황이 나와야한다.
     * 하지만 현재 상황에서는 일단 먼저 DTO를 Entity로 바꿔서 Repository에 저장을 한 후 에러가 발생했기 때문에 rollback이 안되고 db에 원하는 정보가 저장된 상황이다.
     * --> 해결책 : 에러가 발생할 수 있는 서비스 메서드에 @Transactional(org.springframwork.transaction.annotation)을 붙여주면 자동으로 롤백이 적용된다.
     */

    //그에 따른 결과 값(Rollback의 유무) 반환
    return articleList;
  }


}
