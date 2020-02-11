package edu.study.pasudo123.redissample.repository;

import edu.study.pasudo123.redissample.pojo.RedisEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RedisEntityRepository extends CrudRepository<RedisEntity, String> {

    List<RedisEntity> findByReferenceId(final String referenceId);

}
