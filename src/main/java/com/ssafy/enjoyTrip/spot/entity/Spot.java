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
    private int hit;
    private int contentId;
    private double lat;
    private double lang;
    private int sidoId;
    private int gugunId;
    private String desc;
    private String mlevel;
}
