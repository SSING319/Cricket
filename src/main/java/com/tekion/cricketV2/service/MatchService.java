package com.tekion.cricketV2.service;

import com.tekion.cricketV2.dto.Player;
import com.tekion.cricketV2.teams.Team;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MatchService {
    public Team playMatch(Map<String, String> team_line_up, List<String> teamList, Team team, String team_name){

        // this is for storing records/stats of each player in the team
        List<Player> playerDataList = new LinkedList<>();

        // team stats
        int wicketsDown = 0;
        int runsMade = 0;
        int overs = 0;
        int balls = 0;
        int playerOrder = 0;

        //for checking the team is all out
        boolean flag = false;

        //player stats
        String playerName = teamList.get(playerOrder);
        String playerType = team_line_up.get(playerName);
        int playerRuns = 0;

        //logic
        Outerloop :
        for(int i=1; i<=10; i++){
            for(int j=1; j<=6; j++){
                if(wicketsDown == 10){
                    flag = true;
                    break Outerloop;
                }
                balls++;
                Random random = new Random();
                int action = random.nextInt(0, 8);
                if(action == 7){
                    playerDataList.add(new Player(playerName, playerType, playerRuns));
                    wicketsDown++;
                    playerOrder++;
                    if(playerOrder <= 9){
                        playerName = teamList.get(playerOrder);
                        playerType = team_line_up.get(playerName);
                        playerRuns=0;
                    }
                    action=0;
                }
                playerRuns += action;
                runsMade += action;

            }
            balls = 0;
            overs++;
        }

        if(balls==6){overs++; balls=0;}

        //for adding the runs of last playing batsmen if the team is not all out
        if(!flag) playerDataList.add(new Player(playerName, playerType, playerRuns));

        String total_overs = overs+"."+balls;

        //Setters
        team.setOvers(total_overs);
        team.setTeam_name(team_name);
        team.setRuns(runsMade);
        team.setWickets(wicketsDown);
        team.setScoreCard(playerDataList);


        return team;
    }
}
