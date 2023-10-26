package com.tekion.cricketV2.dto;


import com.tekion.cricketV2.series_teams.Series_Team;
import com.tekion.cricketV2.series_teams.Series_Team_A;
import com.tekion.cricketV2.series_teams.Series_Team_B;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Match {
    Series_Team_A series_team_a;
    Series_Team_B series_team_b;
}
