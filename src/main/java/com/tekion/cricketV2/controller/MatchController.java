package com.tekion.cricketV2.controller;

import com.tekion.cricketV2.dao.All_Players_Stats;
import com.tekion.cricketV2.dao.ScoreCard;
import com.tekion.cricketV2.dto.Player;
import com.tekion.cricketV2.repo.PlayersRepo;
import com.tekion.cricketV2.repo.ResultRepo;
import com.tekion.cricketV2.request.Input_Request;
import com.tekion.cricketV2.request.Input_Team_A;
import com.tekion.cricketV2.request.Input_Team_B;
import com.tekion.cricketV2.response.MatchResult;
import com.tekion.cricketV2.service.MatchService;
import com.tekion.cricketV2.match_teams.Team_A;
import com.tekion.cricketV2.match_teams.Team_B;
import com.tekion.cricketV2.util.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v4/match")
public class MatchController {
    @Autowired
    Helper helper;
    @Autowired
    private final MatchService service;
    @Autowired
    Team_A response_team_a;
    @Autowired
    Team_B response_team_b;
    @Autowired
    MatchResult matchResult;
    @Autowired
    private final ResultRepo resultRepo;
    @Autowired
    private final PlayersRepo playersRepo;
    private final All_Players_Stats all_players_stats;
    private final ScoreCard scoreCard;

    public MatchController(MatchService service, ResultRepo resultRepo, PlayersRepo playersRepo, All_Players_Stats allPlayersStats, ScoreCard scoreCard) {
        this.service = service;
        this.resultRepo = resultRepo;
        this.playersRepo = playersRepo;
        all_players_stats = allPlayersStats;
        this.scoreCard = scoreCard;
    }


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

        //match started
        Team_A team_result_a = (Team_A) service.playMatch(team_map_a, team_line_up_a, response_team_a, first_batting, inputRequest.getMatch_overs());
        Team_B team_result_b = (Team_B) service.playMatch(team_map_b, team_line_up_b, response_team_b, second_batting, inputRequest.getMatch_overs());

        //publishing response
        matchResult.setTeam_a(team_result_a);
        matchResult.setTeam_b(team_result_b);
        matchResult.setWinner(matchResult.winner());

        //dao objects init
        scoreCard.setMatchResult(matchResult);

        // adding stats of all players which have played in the match
        List<Player> temp = new LinkedList<>();

        temp.addAll(matchResult.getTeam_a().getScoreCard());
        temp.addAll(matchResult.getTeam_b().getScoreCard());


        all_players_stats.setAllPlayers(temp);


        //persisting player stats and scoreboard in MongoDB
        playersRepo.save(all_players_stats);
        resultRepo.save(scoreCard);

        return matchResult;
    }
    @GetMapping("/playerStats/{player_name}")
    public Player getPlayerStats(@PathVariable String player_name){
        return helper.findPlayerStats(player_name);
    }
}

