package com.tekion.cricketV2.service;

import com.tekion.cricketV2.teams.Team;
import org.springframework.stereotype.Service;

import java.util.Random;
@Service
public class MatchService {
    public Team playMatch(String batting_team, Team team){

        int wicketsDown = 0;
        int runsMade = 0;
        int overs = 0;
        int balls = 0;

        Outerloop :
        for(int i=0; i<10; i++){
            for(int j=0; j<6; j++){
                if(wicketsDown == 10){
                    break Outerloop;
                }
                Random random = new Random();
                int action = random.nextInt(0, 8);
                if(action == 7){
                    wicketsDown++;
                    continue;
                }
                runsMade += action;
                balls++;
            }
            balls = 0;
            overs++;
        }
        String total_overs = overs+"."+balls;

        team.setTeam_name(batting_team);
        team.setRuns(runsMade);
        team.setWickets(wicketsDown);
        team.setOvers(total_overs);

        return team;
    }
}
