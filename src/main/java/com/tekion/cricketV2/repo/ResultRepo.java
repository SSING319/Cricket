package com.tekion.cricketV2.repo;

import com.tekion.cricketV2.dao.ScoreCard;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultRepo extends MongoRepository<ScoreCard, String> {

}
