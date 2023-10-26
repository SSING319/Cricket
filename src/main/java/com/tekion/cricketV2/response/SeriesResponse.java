package com.tekion.cricketV2.response;

import com.tekion.cricketV2.dto.Match;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class SeriesResponse {
    List<Match> matches;
    String series_winner;
}
