package com.fanfq.sbt.dao;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.fanfq.sbt.model.Sbt;


public interface SbtMongoRepostory extends MongoRepository<Sbt, String>{

}
