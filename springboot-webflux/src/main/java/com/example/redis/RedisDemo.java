package com.example.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by sunyong on 2019/1/5.
 */
@Component
public class RedisDemo {

    @Autowired
    private StringRedisTemplate redisTemplate;


    public void testList() {
        String kk = "kk";
        List<String> list = new ArrayList<>();
        list.add("11");
        list.add("1");
        list.add("5");
        list.add("8");
        list.add("4");
        list.add("3");
        redisTemplate.opsForList().leftPushAll(kk, list);
        String s = redisTemplate.opsForList().leftPop(kk);
        String ss = redisTemplate.opsForList().leftPop(kk);
        System.out.println(s + ":" + ss + ":********");
    }

    public void testHash() {
        String key = "key123";
        Map<String, String> map = new HashMap<>();
        map.put("x1", "1");
        map.put("x2", "2");
        map.put("x3", "3");
        map.put("x4", "4");
        redisTemplate.opsForHash().putAll(key, map);
        Object x4 = redisTemplate.opsForHash().get(key, "x4");
        System.out.println("======:" + x4);
        redisTemplate.opsForHash().put(key, "x4", "77");
        Object x41 = redisTemplate.opsForHash().get(key, "x4");
        System.out.println("======:" + x41);
    }

    public void testZSet() {
        String k = "key";
        redisTemplate.opsForZSet().add(k, "k6", 1);
        redisTemplate.opsForZSet().add(k, "k2", 6);
        redisTemplate.opsForZSet().add(k, "k5", 4);
        redisTemplate.opsForZSet().add(k, "k3", 3);
        redisTemplate.opsForZSet().add(k, "k1", 2);
        redisTemplate.opsForZSet().add(k, "k4", 9);
        // k6 1 / k1 2 / k3 3 / k5 4 /
        RedisOperations<String, String> operations = redisTemplate.opsForZSet().getOperations();
        ZSetOperations<String, String> zSet = operations.opsForZSet();
        Set<String> range = zSet.range(k, 2, 5); //下标从0开始
        Iterator<String> i1 = range.iterator();
        while (i1.hasNext()) {
            String next = i1.next();
            System.out.print(next + " ");
        }
        System.out.println("=============");
        RedisZSetCommands.Range range1 = RedisZSetCommands.Range.range();
        range1.lt("k5");// 小于
        range1.gt("k2");// 大于
        Set<String> set = zSet.rangeByLex(k, range1);
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
    }

}
