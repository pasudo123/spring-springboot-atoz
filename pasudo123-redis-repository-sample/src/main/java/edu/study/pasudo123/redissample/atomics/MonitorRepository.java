package edu.study.pasudo123.redissample.atomics;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.lang.management.MonitorInfo;

@Repository
public interface MonitorRepository extends CrudRepository<MonitorInfo, String> {
}
