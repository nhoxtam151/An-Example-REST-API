package com.ducku.entity;

import com.ducku.dto.StudentDTO;
import java.time.LocalDate;
import java.time.Period;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;


@Entity
@Table(name = "student")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {


  @Id
  @SequenceGenerator(
      name = "student_sequence",
      sequenceName = "student_sequence",
      allocationSize = 1
  )
  @GeneratedValue(
      strategy = GenerationType.SEQUENCE,
      generator = "student_sequence"
  )
  private Long id;
  private String fullName;
  private String email;
  private LocalDate dateOfBirth;
  @Transient
  @Getter(AccessLevel.NONE)
  private Integer age;

  public Student(String fullName, String email, LocalDate dateOfBirth) {
    this.fullName = fullName;
    this.email = email;
    this.dateOfBirth = dateOfBirth;

  }

  public Integer getAge() {
    return Period.between(dateOfBirth,LocalDate.now()).getYears();
  }
}
