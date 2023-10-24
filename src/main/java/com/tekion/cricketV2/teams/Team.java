package com.tekion.cricketV2.teams;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Team {
    String team_name;
    int runs;
    String overs;
    int wickets;
}
