package com.tekion.cricketV2.match_teams;

import com.tekion.cricketV2.dto.Player;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Team {
    String team_name;
    int runs;
    String overs;
    int wickets;
    List<Player> scoreCard;
}
