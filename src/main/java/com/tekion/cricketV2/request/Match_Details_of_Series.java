package com.tekion.cricketV2.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Match_Details_of_Series {
    String series_id;
    int match_number;
}
