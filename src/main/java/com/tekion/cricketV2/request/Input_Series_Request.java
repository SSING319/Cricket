package com.tekion.cricketV2.request;

import com.tekion.cricketV2.series_teams.Series_Team_A;
import com.tekion.cricketV2.series_teams.Series_Team_B;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Input_Series_Request {
    String team_name_a;
    String team_name_b;
    int no_of_matches;
    int overs;
}
