package com.example.use_mustache.rest_api_controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController // 일반적인 controller는 view와 model을 연결해주는 역할을 하는 것
// 그에 반해 RestController는 url을 요청받아서 해당하는 데이터를 json으로 전달해주는 역할을 하는 것
public class FirstApiController {
  // 조회
  @GetMapping("/api/hello")
  public String hello(){
  
    return "Hello world!"; 
  }
  
}
