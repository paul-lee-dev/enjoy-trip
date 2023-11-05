package com.ssafy.enjoyTrip.spot.dao;

import com.ssafy.enjoyTrip.spot.entity.dto.GetSpotRes;

import java.util.List;
import java.util.Map;

public interface SpotDao {
    List<GetSpotRes> getSpotList(Map<String, String> map);
    GetSpotRes getSpot(int spotId);
//    List<GetSpotRes> searchSpot(String keyword);
    List<String[]> getSidoList();
    List<String[]> getGugunList(int sidoId);
}
