package com.ssafy.enjoyTrip.spot.entity.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class GugunDto {

    private int gugunCode;
    private String gugunName;
    private int sidoCode;
}
