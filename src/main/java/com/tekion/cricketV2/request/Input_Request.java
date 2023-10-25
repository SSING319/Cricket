package com.tekion.cricketV2.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Input_Request {
    Input_Team_A input_team_a;
    Input_Team_B input_team_b;
}
