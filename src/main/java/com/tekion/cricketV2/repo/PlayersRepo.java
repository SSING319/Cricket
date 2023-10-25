package com.tekion.cricketV2.repo;

import com.tekion.cricketV2.dao.All_Players_Stats;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayersRepo extends MongoRepository<All_Players_Stats, String> {
}
