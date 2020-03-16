package edu.study.pasudo123.redissample.dao;

import edu.study.pasudo123.redissample.pojo.RedisDummy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class RedisDummyDao {

    private static final String KEY = "KEY";

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void push(final List<RedisDummy> redisDummyList){
        redisTemplate.opsForList().leftPush(KEY, redisDummyList);
    }

    public List<RedisDummy> popAllDataByKey(final String key){

        final List<Object> list = redisTemplate.opsForList().range(key, 0, -1);

        final List<RedisDummy> redisDummies = new ArrayList<>();

        assert list != null;
        for(Object element : list) {
            redisDummies.add((RedisDummy) element);
        }

        return redisDummies;
    }
}
