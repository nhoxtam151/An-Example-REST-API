package com.ducku.service;

import com.ducku.dto.StudentDTO;
import com.ducku.entity.Student;
import com.ducku.exception.CustomRunTimeException;
import com.ducku.repository.StudentRepository;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Data
public class StudentService {

  private final StudentRepository studentRepository;
  private final Pattern VALID_EMAIL_ADDRESS_REGEX =
      Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);


  public List<StudentDTO> getStudents() {

    return studentRepository.findAll().stream().map(
        student -> new StudentDTO(student.getFullName(), student.getEmail(),
            student.getDateOfBirth(), student.getAge())).collect(
        Collectors.toList());
  }

  public void registerStudent(StudentDTO studentDTO) {
    if (studentRepository.findStudentByEmail(studentDTO.getEmail()).isPresent()) {
      throw new CustomRunTimeException("Email taken");
    }
    if (studentDTO.getEmail() == null || studentDTO.getEmail().isEmpty()
        || studentDTO.getEmail().trim().isBlank()) {
      throw new CustomRunTimeException("Invalid email");
    }
    Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(studentDTO.getEmail());
    if (matcher.find()) {
      Student student = new Student();
      student.setEmail(studentDTO.getEmail());
      student.setAge(studentDTO.getAge());
      student.setFullName(studentDTO.getFullName());
      student.setDateOfBirth(studentDTO.getDateOfBirth());
      studentRepository.save(student);
    } else {
      throw new CustomRunTimeException("Invalid email");
    }
  }

  public void deleteStudent(Long id) {
    if (id == null || id <= 0) {
      return;
    }
    studentRepository.deleteById(id);
  }

  @Transactional(rollbackFor = Exception.class)
  public void updateStudent(Long id, String email, String fullName) {
    Student studentInDB = studentRepository.findById(id).orElseThrow(
        () -> new CustomRunTimeException("Student with id" + id + " does not exist!!!"));
    if (fullName == null || fullName.trim().isBlank() || fullName.isEmpty()) {
      throw new CustomRunTimeException("Invalid fullName");
    }
    if (studentInDB.getEmail() == null || studentInDB.getEmail().isEmpty()
        || studentInDB.getEmail().trim().isBlank()) {
      throw new CustomRunTimeException("Invalid email");
    } else {
      Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
      if (matcher.find()) {
        studentInDB.setEmail(email);
        studentInDB.setFullName(fullName);
      } else {
        throw new CustomRunTimeException("Invalid email");
      }
    }

  }
}
