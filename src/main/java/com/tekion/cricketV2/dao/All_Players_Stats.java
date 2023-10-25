package com.tekion.cricketV2.dao;

import com.tekion.cricketV2.dto.Player;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.util.List;

@Document(collection = "match_players_stats")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class All_Players_Stats {
    @Id
    String id;
    List<Player> allPlayers;
}
