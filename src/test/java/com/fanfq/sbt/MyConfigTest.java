package com.fanfq.sbt;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fanfq.sbt.config.MyConfig;



/*
 * 自定义参数测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MyConfigTest {
	
	@Autowired
	private MyConfig myConfig;
	
	@Test
	public void test() {
		
		System.out.println(myConfig.getSimpleProp());
		System.out.println(myConfig.getArrayProps());
		System.out.println(myConfig.getListProp1());
		System.out.println(myConfig.getListProp2());
		System.out.println(myConfig.getMapProps());
	}

}
