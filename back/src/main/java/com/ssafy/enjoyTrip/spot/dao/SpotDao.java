package com.ssafy.enjoyTrip.spot.dao;

import com.ssafy.enjoyTrip.spot.entity.dto.GugunDto;
import com.ssafy.enjoyTrip.spot.entity.dto.SidoDto;
import com.ssafy.enjoyTrip.spot.entity.dto.GetSpotRes;

import java.util.List;
import java.util.Map;

public interface SpotDao {
    List<GetSpotRes> getSpotList(Map<String, Integer> map);
    GetSpotRes getSpotDetail(int spotId);
    List<GetSpotRes> searchSpot(String keyword);
    List<SidoDto> getSidoList();
    List<GugunDto> getGugunList(int sidoId);
}
