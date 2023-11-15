package com.ssafy.enjoyTrip.user.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class SnsInfoDto {
    private int userId;
    private String email;
    private String name;
    private String profileImgUrl;
    private String snsType;
}
