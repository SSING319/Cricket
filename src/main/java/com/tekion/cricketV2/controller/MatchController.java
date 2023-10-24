package com.tekion.cricketV2.controller;

import com.tekion.cricketV2.model.Match;
import com.tekion.cricketV2.model.MatchResult;
import com.tekion.cricketV2.service.MatchService;
import com.tekion.cricketV2.teams.Team_A;
import com.tekion.cricketV2.teams.Team_B;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class MatchController {
    @Autowired
    private MatchService service;
    @Autowired
    Team_A team_a;
    @Autowired
    Team_B team_b;
    @Autowired
    MatchResult matchResult;
    @PostMapping
    public MatchResult startMatch(@RequestBody Match match) throws Exception {
        if (match == null) {
            throw new Exception("Bad Request");
        }

        String first_batting = match.getTeam_A();
        String second_batting = match.getTeam_B();

        team_a = (Team_A) service.playMatch(first_batting, team_a);
        team_b = (Team_B) service.playMatch(second_batting, team_b);

        matchResult.setTeam_a(team_a);
        matchResult.setTeam_b(team_b);
        matchResult.setWinner(matchResult.winner());


        return matchResult;

    }
}

