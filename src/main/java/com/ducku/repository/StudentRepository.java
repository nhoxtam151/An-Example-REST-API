package com.ducku.repository;

import com.ducku.entity.Student;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

  List<Student> findAll();

  Optional<Student> findStudentByEmail(String email);

  void deleteById(Long id);

  Optional<Student> findByEmail(String email);
}
