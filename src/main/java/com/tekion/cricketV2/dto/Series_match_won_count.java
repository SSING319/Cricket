package com.tekion.cricketV2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Series_match_won_count {

    String series_team_name_a;
    String series_team_name_b;
    int series_team_a;
    int series_team_b;

    public void team_a_won(){
        series_team_a++;
    }
    public void team_b_won(){
        series_team_b++;
    }

    public String winner(){
        if(series_team_a>series_team_b) return series_team_name_a+" won the series!!";
        else if(series_team_a<series_team_b) return series_team_name_b+" won the series!!";
        else return "series draw";
    }
}
