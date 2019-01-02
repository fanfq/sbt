package com.fanfq.sbt;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSONObject;
import com.fanfq.sbt.dao.SbtDao;
import com.fanfq.sbt.model.Sbt;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MysqlTest {

	@Autowired
	private SbtDao sbtDao;
	
	@Test
	public void save() {
		//redis storage
		Sbt sbt = new Sbt();
		sbt.setName("zhangsan");
		sbt.setPassword("pwd123");
		
		System.out.println(sbtDao.insertSbt(sbt));
	}
	
	@Test
	public void all() {
		System.out.println(JSONObject.toJSONString(sbtDao.selectAll()));
	}
}
