package edu.study.pasudo123.redissample.atomics;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonitorRepository extends CrudRepository<Monitor, String> {}
