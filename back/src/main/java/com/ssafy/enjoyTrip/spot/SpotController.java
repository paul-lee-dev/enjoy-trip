package com.ssafy.enjoyTrip.spot;


import com.ssafy.enjoyTrip.common.BaseException;
import com.ssafy.enjoyTrip.common.BaseResponse;
import com.ssafy.enjoyTrip.spot.entity.dto.GetSpotRes;
import com.ssafy.enjoyTrip.spot.entity.dto.GugunDto;
import com.ssafy.enjoyTrip.spot.entity.dto.SidoDto;
import com.ssafy.enjoyTrip.spot.service.SpotService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(value = "Spot API")
@RestController
@CrossOrigin(origins = {"*"})//다른 서버에서 AJax 요청이 와도 서비스 되도록 설정
@RequestMapping("/spots")
public class SpotController {
    private final SpotService spotService;
    public SpotController(SpotService spotService) {
        this.spotService = spotService;
    }


    @GetMapping
    public ResponseEntity<?> getSido() throws BaseException {
            List<SidoDto> sidos = spotService.getSidoList();
            return ResponseEntity
                    .ok()
                    .body(new BaseResponse<>(sidos));
    }

    @ApiOperation(value = "구군 조회")
    @GetMapping("/{sidoId}")
    public ResponseEntity<?> getGugun(@PathVariable int sidoId) throws BaseException{
            List<GugunDto> guguns = spotService.getGugunList(sidoId);
            return ResponseEntity
                    .ok()
                    .body(new BaseResponse<>(guguns));
    }


    @ApiOperation(value = "구군별 관광지 조회")
    @GetMapping("/{contentId}/{sidoId}/{gugunId}")
    public ResponseEntity<?> getSpotList(@PathVariable() int contentId, @PathVariable() int sidoId, @PathVariable() int gugunId) throws BaseException {
        Map<String, Integer> map = new HashMap<>();
        map.put("contentId",contentId);
        map.put("sidoId",sidoId);
        map.put("gugunId",gugunId);
        System.out.println(map);
        List<GetSpotRes> spotList = spotService.getSpotList(map);
        System.out.println("spotList.size() = " + spotList.size());
        return ResponseEntity
                .ok()
                .body(new BaseResponse<>(spotList));
    }

    @ApiOperation(value = "키워드 관광지 검색")
    @GetMapping("/search/{word}")
    public ResponseEntity<?> searchSpot(@PathVariable("word") String word) throws BaseException{
        List<GetSpotRes> spotList=spotService.searchSpot(word);
        return ResponseEntity
                .ok()
                .body(new BaseResponse<>(spotList));
    }

    @ApiOperation(value = "관광지 상세정보 조회")
    @GetMapping("/detail/{spotId}")
    public ResponseEntity<?> getSpotDetail(@PathVariable() int spotId) throws BaseException {
        GetSpotRes spot = spotService.getSpotDetail(spotId);
        return ResponseEntity
                .ok()
                .body(new BaseResponse<>(spot));
    }
}