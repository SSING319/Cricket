package com.tekion.cricketV2.series_teams;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Series_Team {
    String team_name;
    int wickets;
    String overs;
    int runs;
}
