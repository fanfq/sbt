package com.fanfq.sbt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.fanfq.sbt.model.Sbt;

@Mapper
@Repository
public interface SbtDao {

	public int insertSbt(Sbt sbt);
	
	@Select("select * from t_sbt")
	public List<Sbt> selectAll();
}
