package com.tekion.cricketV2.util;

import com.tekion.cricketV2.dao.All_Players_Stats;
import com.tekion.cricketV2.dto.Player;
import com.tekion.cricketV2.repo.PlayersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
public class Helper {
    @Autowired
    private PlayersRepo playersRepo;

    public Player findPlayerStats(String player_name){
       List<Player> playerList = playersRepo.findAll().get(0).getAllPlayers();
       Optional<Player> foundPlayer = playerList.stream()
               .filter(player -> player.getPlayerName().equals(player_name))
               .findFirst();
       return foundPlayer.orElse(null);
    }
}
