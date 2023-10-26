package com.tekion.cricketV2.response;

import com.tekion.cricketV2.match_teams.Team_A;
import com.tekion.cricketV2.match_teams.Team_B;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class MatchResult {
    Team_A team_a;
    Team_B team_b;
    String winner;

    public String winner(){
        if(team_a.getRuns()> team_b.getRuns()) return team_a.getTeam_name()+" won the match!!";
        else if(team_a.getRuns() < team_b.getRuns()) return team_b.getTeam_name()+" won the match!!";
        else return "its a draw";
    }
}
