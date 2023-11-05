package com.ssafy.enjoyTrip.spot.service;

import com.ssafy.enjoyTrip.common.BaseException;
import com.ssafy.enjoyTrip.spot.entity.dto.GetSpotRes;

import java.util.List;
import java.util.Map;

public interface SpotService {
    List<GetSpotRes> getSpotList(Map<String, String> map) throws BaseException;
//    List<GetSpotRes> searchSpot(String keyword) throws BaseException;
    GetSpotRes getSpot(int spotId) throws BaseException;
    List<String[]> getSidoList() throws BaseException;
    List<String[]> getGugunList(int sidoId) throws BaseException;
}
