package com.ssafy.enjoyTrip.spot.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@Builder
@ToString
public class SidoDto {

    private int sidoCode;
    private String sidoName;
}
