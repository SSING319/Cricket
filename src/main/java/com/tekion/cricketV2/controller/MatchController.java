package com.tekion.cricketV2.controller;

import com.tekion.cricketV2.request.Input_Request;
import com.tekion.cricketV2.request.Input_Team_A;
import com.tekion.cricketV2.request.Input_Team_B;
import com.tekion.cricketV2.response.MatchResult;
import com.tekion.cricketV2.service.MatchService;
import com.tekion.cricketV2.teams.Team_A;
import com.tekion.cricketV2.teams.Team_B;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v2")
public class MatchController {
    @Autowired
    private MatchService service;
    @Autowired
    Team_A response_team_a;
    @Autowired
    Team_B response_team_b;
    @Autowired
    MatchResult matchResult;
    @Autowired
    Input_Team_A input_team_a;
    @PostMapping
    public MatchResult startMatch(@RequestBody Input_Request inputRequest) throws Exception {
        if (inputRequest == null) {
            throw new Exception("Bad Request");
        }

        Input_Team_A team_a = inputRequest.getInput_team_a();
        Input_Team_B team_b = inputRequest.getInput_team_b();

        //this is for the name of the team
        String first_batting = team_a.getName();
        String second_batting = team_b.getName();

        // This is for labeling each player as batsmen or bowler
        Map<String, String> team_map_a = team_a.lineUp(team_a.getBatsmen(), team_a.getBowlers());
        Map<String, String> team_map_b = team_b.lineUp(team_b.getBatsmen(), team_b.getBowlers());

        // this is the batting lineup of each team
        List<String> team_line_up_a = team_a.teamList(team_a.getBatsmen(), team_a.getBowlers());
        List<String> team_line_up_b = team_b.teamList(team_b.getBatsmen(), team_b.getBowlers());


        Team_A team_result_a = (Team_A) service.playMatch(team_map_a, team_line_up_a, response_team_a, first_batting);
        Team_B team_result_b = (Team_B) service.playMatch(team_map_b, team_line_up_b, response_team_b, second_batting);

        matchResult.setTeam_a(team_result_a);
        matchResult.setTeam_b(team_result_b);
        matchResult.setWinner(matchResult.winner());

        return matchResult;

    }
}

