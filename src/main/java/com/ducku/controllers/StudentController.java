package com.ducku.controllers;

import com.ducku.dto.StudentDTO;
import com.ducku.service.StudentService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/students")
@AllArgsConstructor
public class StudentController {

  private final StudentService studentService;

  @GetMapping
  public List<StudentDTO> getStudents() {
    return studentService.getStudents();
  }

  @PostMapping
  public void registerStudent(@RequestBody StudentDTO studentDTO) {
    //LocalDate.of(2000, Month.JANUARY,  3);
    studentService.registerStudent(studentDTO);
  }


  @PutMapping(path = "{studentId}")
  public void updateStudent(@PathVariable("studentId") Long studentId,
      @RequestBody StudentDTO studentDTO) {
    studentService.updateStudent(studentId, studentDTO.getEmail(), studentDTO.getFullName());
  }

  @DeleteMapping(path = "{studentId}")
  public void deleteStudent(@PathVariable("studentId") Long id) {
    studentService.deleteStudent(id);
  }

}
