package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.dto.MemberForm;
import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;

@Controller
public class MemberController {
  @Autowired
  MemberRepository memberRepository;
  
  @GetMapping("/signup")
  public String signUpPage() {
    return "members/new.mmustache";
  }

  @PostMapping("/join")
  public String join(MemberForm memberForm) {    
    System.out.println(memberForm.toString());
    Member member = memberForm.toEntity();
    System.out.println(member.toString());
    return "";
  }
}
