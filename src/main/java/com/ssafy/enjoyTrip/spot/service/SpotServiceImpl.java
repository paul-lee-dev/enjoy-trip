package com.ssafy.enjoyTrip.spot.service;

import com.ssafy.enjoyTrip.common.BaseException;
import com.ssafy.enjoyTrip.spot.dao.SpotDao;
import com.ssafy.enjoyTrip.spot.entity.dto.GugunDto;
import com.ssafy.enjoyTrip.spot.entity.dto.SidoDto;
import com.ssafy.enjoyTrip.spot.entity.dto.GetSpotRes;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static com.ssafy.enjoyTrip.common.BaseResponseStatus.DB_ERROR;

@Service
public class SpotServiceImpl implements SpotService{

    private final SpotDao spotDao;

    public SpotServiceImpl(SpotDao spotDao) { this.spotDao = spotDao; }

    public List<GetSpotRes> getSpotList(Map<String, Integer> map) throws BaseException {
        List<GetSpotRes> spots;
        try {
            spots = spotDao.getSpotList(map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(DB_ERROR);
        }
        return spots;
    }

    @Override
    public List<GetSpotRes> searchSpot(String keyword) throws BaseException {
        List<GetSpotRes> spots;
        try {
            spots = spotDao.searchSpot(keyword);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(DB_ERROR);
        }
        return spots;
    }


    public GetSpotRes getSpotDetail(int spotId) throws BaseException {
        GetSpotRes spot;
        try {
            spot = spotDao.getSpotDetail(spotId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(DB_ERROR);
        }
        return spot;
    }

    public List<SidoDto> getSidoList() throws BaseException {
        try {
            System.out.println(spotDao.getSidoList());
            return spotDao.getSidoList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(DB_ERROR);
        }
    }

    public List<GugunDto> getGugunList(int sidoId) throws BaseException {
        List<GugunDto> guguns;
        try {
            guguns = spotDao.getGugunList(sidoId);
        } catch (Exception e){
            e.printStackTrace();
            throw new BaseException(DB_ERROR);
        }
        return guguns;
    }
}
