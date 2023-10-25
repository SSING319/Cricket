package com.tekion.cricketV2.dao;

import com.tekion.cricketV2.response.MatchResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;


@Document(collection = "score_card")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class ScoreCard {
    @Id
    String id;
    MatchResult matchResult;
}
