package com.ssafy.enjoyTrip.spot.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class Spot {
    private int spotId;
    private String title;
    private String address;
    private String tel;
    private String image;
    private String hit;
    private String theme;
    private String contentId;
    private String lat;
    private String lang;
    private String sidoId;
    private String gugunId;
    private String desc;
    private String memo;
}
