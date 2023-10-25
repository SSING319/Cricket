package com.tekion.cricketV2.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Input_Team {
    String name;
    ArrayList<String> batsmen;
    ArrayList<String> bowlers;

    public Map<String, String> lineUp(ArrayList<String> batsmen, ArrayList<String> bowlers){
        Map<String, String> teamLineUp = new HashMap<>();
        for(String player : batsmen) teamLineUp.put(player, "BatsMen");
        for (String player : bowlers) teamLineUp.put(player, "Bowler");
        return teamLineUp;
    }

    public List<String> teamList(ArrayList<String> batsmen, ArrayList<String> bowlers){
        List<String> team = new ArrayList<>();
        team.addAll(batsmen);
        team.addAll(bowlers);
        return team;
    }
}
