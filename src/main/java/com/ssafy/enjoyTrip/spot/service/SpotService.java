package com.ssafy.enjoyTrip.spot.service;

import com.ssafy.enjoyTrip.common.BaseException;
import com.ssafy.enjoyTrip.spot.entity.dto.GugunDto;
import com.ssafy.enjoyTrip.spot.entity.dto.SidoDto;
import com.ssafy.enjoyTrip.spot.entity.dto.GetSpotRes;

import java.util.List;
import java.util.Map;

public interface SpotService {
    List<GetSpotRes> getSpotList(Map<String, Integer> map) throws BaseException;
    List<GetSpotRes> searchSpot(String keyword) throws BaseException;
    GetSpotRes getSpotDetail(int spotId) throws BaseException;
    List<SidoDto> getSidoList() throws BaseException;
    List<GugunDto> getGugunList(int sidoId) throws BaseException;
}
