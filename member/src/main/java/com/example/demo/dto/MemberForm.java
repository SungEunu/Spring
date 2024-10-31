package com.example.demo.dto;

import com.example.demo.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberForm {
  private String email;
  private String password;

  public Member toEntity() {
    return new Member(1, email, password);
  }

}
