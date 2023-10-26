package com.tekion.cricketV2.controller;

import com.tekion.cricketV2.dao.Series;
import com.tekion.cricketV2.dto.Match;
import com.tekion.cricketV2.request.Match_Details_of_Series;
import com.tekion.cricketV2.dto.Series_match_won_count;
import com.tekion.cricketV2.repo.SeriesRepo;
import com.tekion.cricketV2.request.Input_Series_Request;
import com.tekion.cricketV2.response.SeriesResponse;
import com.tekion.cricketV2.service.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("api/v4/series")
public class SeriesController {
    @Autowired
    private SeriesResponse seriesResponse;
    @Autowired
    private SeriesService seriesService;
    @Autowired
    Series_match_won_count series_match_won_count;
    @Autowired
    SeriesRepo seriesRepo;
    @Autowired
    Series series;
    @PostMapping
    public SeriesResponse playSeries(@RequestBody Input_Series_Request input_series_request){

        series_match_won_count.setSeries_team_name_a(input_series_request.getTeam_name_a());
        series_match_won_count.setSeries_team_name_b(input_series_request.getTeam_name_b());

        List<Match> matches = new ArrayList<>();
        int no_of_matches = input_series_request.getNo_of_matches();


        while(no_of_matches-->0){
            matches.add(
                    seriesService.playSeriesMatch(input_series_request.getTeam_name_a(),
                                    input_series_request.getTeam_name_b(),
                                    input_series_request.getOvers()
                    )
            );
        }
        System.out.println(series_match_won_count.winner());
        seriesResponse.setMatches(matches);
        seriesResponse.setSeries_winner(series_match_won_count.winner());

        series.setSeriesResponse(seriesResponse);
        String seriesId = input_series_request.getTeam_name_a()+" vs "+input_series_request.getTeam_name_b();
        series.setId(seriesId);

        seriesRepo.save(series);
        return seriesResponse;
    }
    @PostMapping("/matchDetails")
    public Match getMatchDetailsOfSeries(@RequestBody Match_Details_of_Series match_details_of_series){
        // Assuming you have a SeriesRepository and match_details_of_series available

        Optional<Series> seriesOptional = seriesRepo.findById(match_details_of_series.getSeries_id());

        if (seriesOptional.isPresent()) {
            Series series = seriesOptional.get();

            if (match_details_of_series.getMatch_number() < series.getSeriesResponse().getMatches().size()) {
                return series.getSeriesResponse().getMatches().get(match_details_of_series.getMatch_number()-1);
                // Now you have the 'match' object for further processing
            } else {
                throw new NoSuchElementException("Match Number is not correct");
            }
        } else {
            // Handle the case where the series with the provided ID is not found
            throw new NoSuchElementException("Series with this ID doesn't exists");
        }


    }

    @GetMapping("/seriesDetails/{series_id}")
    public Series getSeriesDetails(@PathVariable String series_id){
        // Assuming you have a SeriesRepository and match_details_of_series available

        Optional<Series> seriesOptional = seriesRepo.findById(series_id);

        if (seriesOptional.isPresent()) {
            return seriesOptional.get();
        } else {
            // Handle the case where the series with the provided ID is not found
            throw new NoSuchElementException("Series with this ID doesn't exists");
        }


    }
}
