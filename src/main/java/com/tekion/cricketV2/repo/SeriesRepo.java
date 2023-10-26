package com.tekion.cricketV2.repo;

import com.tekion.cricketV2.dao.Series;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeriesRepo extends MongoRepository<Series, String> {
}
