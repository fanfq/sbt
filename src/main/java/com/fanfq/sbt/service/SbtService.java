package com.fanfq.sbt.service;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.fanfq.common.util.redis.RedisUtil;
import com.fanfq.sbt.dao.SbtDao;
import com.fanfq.sbt.model.Sbt;

@Service
public class SbtService implements SbtDao{
	
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SbtDao sbtDao;
	


	@Autowired
	private StringRedisTemplate redisTemplate;
	
//	// inject the actual template
    @Autowired
    private RedisTemplate<String, String> template;
//
//    // inject the template as ListOperations
//    // can also inject as Value, Set, ZSet, and HashOperations
//    @Resource(name="redisTemplate")
//    private ListOperations<String, String> listOps;
//
//    public void redisInsert(Sbt sbt) {
//        //listOps.leftPush(userId, url.toExternalForm());
//        // or use template directly
//        //redisTemplate.boundListOps(userId).leftPush(url.toExternalForm());
//    	template.s
//    }

	@Override
	public int insertSbt(Sbt sbt) {
		
		//redis storage
		String key = "key:"+System.currentTimeMillis();
		String value = JSONObject.toJSONString(sbt);
//		redisUtil.setEx(key, value, 10, TimeUnit.MINUTES);
		redisTemplate.opsForValue().set(key, value, 10, TimeUnit.MINUTES);
		return sbtDao.insertSbt(sbt);
	}

	/**
	 * 根据id获取Person对象，使用缓存
	 * @param id
	 * @return Person对象
	 */
	@Cacheable(value="selectAll", sync=true)
	@Override
	public List<Sbt> selectAll() {
		return sbtDao.selectAll();
	}
	
	

}
