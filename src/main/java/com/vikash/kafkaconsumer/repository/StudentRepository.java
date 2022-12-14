package com.vikash.kafkaconsumer.repository;

import com.vikash.kafkaconsumer.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Long> {
    boolean existsByPhoneNumber(String phoneNumber);

    @Query("select s from Student s")
    List<Student> getAllStudents();

    @Query("select s from Student s where s.phoneNumber=:phoneNumber")
    Student getStudentByPhoneNumber(@Param("phoneNumber") String phoneNumber);


}
