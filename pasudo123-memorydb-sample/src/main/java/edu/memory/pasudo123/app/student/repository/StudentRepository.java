package edu.memory.pasudo123.app.student.repository;

import edu.memory.pasudo123.app.student.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by pasudo123 on 2019-11-09
 * Email: oraedoa@gmail.com
 **/
@Repository
public interface StudentRepository extends CrudRepository<Student, String> {
}
