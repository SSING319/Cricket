package com.tekion.cricketV2.dao;

import com.tekion.cricketV2.response.SeriesResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Document(collection = "series")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Series {
    @Id
    String id;
    SeriesResponse seriesResponse;
}
