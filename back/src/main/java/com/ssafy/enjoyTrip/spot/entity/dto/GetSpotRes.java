package com.ssafy.enjoyTrip.spot.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class GetSpotRes{
        private int spotId;
        private String title;
        private String address;
        private String tel;
        private String image;
        private int hit;
        private int contentId;
        private double lat;
        private double lon;
        private int sidoCode;
        private int gugunCode;
        private String description;
        private String mlevel;
}
