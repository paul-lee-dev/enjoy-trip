package com.ssafy.enjoyTrip.spot.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class GetSpotRes{
        private int spotId;
        private String title;
        private String address;
        private String tel;
        private String image;
        private String hit;
//        private String theme;
        private String contentId;
        private String lat;
        private String lon;
        private String sidoCode;
        private String gugunCode;
        private String description;
//        private String memo;
}
