package com.ducku.dto;

import java.time.LocalDate;
import java.time.Period;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
public class StudentDTO {

  private Long id;
  private String fullName;
  private String email;
  private LocalDate dateOfBirth;
  @Getter(AccessLevel.NONE)
  private Integer age;


  public StudentDTO(String fullName, String email, LocalDate dateOfBirth, Integer age) {
    this.fullName = fullName;
    this.email = email;
    this.dateOfBirth = dateOfBirth;
    this.age = age;
  }

  public Integer getAge() {
    return Period.between(dateOfBirth,LocalDate.now()).getYears();
  }

}
