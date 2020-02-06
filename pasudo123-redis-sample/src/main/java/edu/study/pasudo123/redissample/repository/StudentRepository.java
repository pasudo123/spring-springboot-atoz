package edu.study.pasudo123.redissample.repository;

import edu.study.pasudo123.redissample.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<Student, String> { }
