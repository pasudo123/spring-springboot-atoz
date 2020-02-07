package edu.study.pasudo123.redissample.repository;

import edu.study.pasudo123.redissample.model.Teacher;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends CrudRepository<Teacher, String> {

    Teacher findByName(final String name);

}
