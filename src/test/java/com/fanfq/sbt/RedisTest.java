package com.fanfq.sbt;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSONObject;
import com.fanfq.common.util.redis.RedisUtil;

@RunWith(SpringRunner.class)
@SpringBootTest

public class RedisTest {
	
	@Autowired
    private RedisTemplate<String, String> redisTemplate;
	
	@Autowired
	RedisUtil redisUtil; //使用自定义的redis工具类
	
	String key = null;
	
	@Before
	public void init() {
		key = "testkey:"+System.currentTimeMillis();
		save();
	}
	
	public void save() {
		//redis storage
		Map<String,Object> map = new HashMap<>();
		map.put("name", "zhangsan");
		map.put("ts", System.currentTimeMillis());
		String value = JSONObject.toJSONString(map);
//		redisUtil.setEx(key, value, 10, TimeUnit.MINUTES);
		redisTemplate.opsForValue().set(key, value, 10, TimeUnit.MINUTES);
		
	}
	
	@Test
	public void get() {
		System.out.println(JSONObject.toJSONString(redisUtil.get(key)));
//		System.out.println(JSONObject.toJSONString(redisTemplate.opsForValue().get(key)));
	}

}
