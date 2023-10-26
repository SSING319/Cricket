package com.tekion.cricketV2.service;

import com.tekion.cricketV2.dto.Match;
import com.tekion.cricketV2.dto.Player;
import com.tekion.cricketV2.dto.Series_match_won_count;
import com.tekion.cricketV2.series_teams.Series_Team;
import com.tekion.cricketV2.series_teams.Series_Team_A;
import com.tekion.cricketV2.series_teams.Series_Team_B;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
@Service
public class SeriesService {
    @Autowired
    Series_match_won_count series_match_won_count;

    public Match playSeriesMatch(String team_a, String team_b, int overs){
        Series_Team_A seriesTeamA = new Series_Team_A();
        Series_Team_B seriesTeamB = new Series_Team_B();
        Match match = new Match();

        play(team_a, overs, seriesTeamA);
        play(team_b, overs, seriesTeamB);

        match.setSeries_team_a(seriesTeamA);
        match.setSeries_team_b(seriesTeamB);

        if(seriesTeamA.getRuns()>seriesTeamB.getRuns()) series_match_won_count.team_a_won();
        else if(seriesTeamA.getRuns()<seriesTeamB.getRuns()) series_match_won_count.team_b_won();
        return match;
    }

    private void play(String team_name, int overs, Series_Team series_team) {

        int wicketsDown = 0;
        int runsMade = 0;
        int current_over = 0;
        int balls = 0;

        Outerloop :
        for(int i=1; i<=10; i++){
            for(int j=1; j<=6; j++){
                if(wicketsDown == 10){
                    break Outerloop;
                }
                balls++;
                Random random = new Random();
                int action = random.nextInt(0, 8);
                if(action == 7){
                    wicketsDown++;

                    action=0;
                }
                runsMade += action;

            }
            balls = 0;
            current_over++;
        }

        if(balls==6){current_over++; balls=0;}
        String total_overs = current_over+"."+balls;
        // Set the results in the series_team object
        series_team.setTeam_name(team_name);
        series_team.setRuns(runsMade);
        series_team.setWickets(wicketsDown);
        series_team.setOvers(total_overs);
    }


}
