package com.fanfq.sbt;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSONObject;
import com.fanfq.sbt.dao.SbtMongoRepostory;
import com.fanfq.sbt.model.Sbt;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoTest {

	
	@Autowired
    private SbtMongoRepostory sbtMongoRepostory;
	
	String key = null;
	
	@Before
	public void init() {
		key = "testkey:"+System.currentTimeMillis();
		save();
	}
	
	public void save() {
		//redis storage
		Sbt sbt = new Sbt();
		sbt.setName("zhangsan");
		sbt.setPassword("pwd123");
		
		Sbt s = sbtMongoRepostory.save(sbt);
		System.out.println(JSONObject.toJSONString(s));
		
	}
	
	@Test
	public void all() {
		System.out.println(JSONObject.toJSONString(sbtMongoRepostory.findAll()));
	}
}
