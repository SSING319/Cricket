package com.tekion.cricketV2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Player {
    String playerName;
    String playerType;
    String player_team_name;
    int runsScored;
}
