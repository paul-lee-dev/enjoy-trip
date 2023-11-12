package com.ssafy.enjoyTrip.spot;


import com.ssafy.enjoyTrip.common.constant.Constants;
import com.ssafy.enjoyTrip.spot.entity.Spot;
import com.ssafy.enjoyTrip.spot.entity.dto.GetSpotRes;
import com.ssafy.enjoyTrip.spot.service.SpotService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ssafy.enjoyTrip.common.constant.Constants.PAGE_SIZE;

@Api(value = "Spot API")
@RestController
@CrossOrigin(origins = {"*"})//다른 서버에서 AJax 요청이 와도 서비스 되도록 설정
@RequestMapping("/spot")
public class SpotController {
    private final SpotService spotService;

    public SpotController(SpotService spotService) {
        this.spotService = spotService;
    }

    @ApiOperation(value = "시/도 목록 반환")
    @GetMapping("/sido")
    public ResponseEntity<?> getSido() {
        try {
            List<String[]> sidos = spotService.getSidoList();
            if (sidos != null && !sidos.isEmpty()) {
                return new ResponseEntity<List<String[]>>(sidos, HttpStatus.OK);
            } else {
                return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @GetMapping("/{sidoId}")
    public ResponseEntity<?> getSido(@PathVariable() int sidoId) {
        try {
            List<String[]> guguns = spotService.getGugunList(sidoId);
            if (guguns != null && !guguns.isEmpty()) {
                return new ResponseEntity<List<String[]>>(guguns, HttpStatus.OK);
            } else {
                return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @ApiOperation(value = "관광지 검색")
//    @GetMapping("/{sidoId}/{gugunid}")
//    public ResponseEntity<?> getSpotList(@PathVariable() int sidoId, @PathVariable() int gugunId) {
    @GetMapping
    public ResponseEntity<?> getSpotList(@RequestParam(required = false) String key,
                                         @RequestParam(required = false) String word,
                                         @RequestParam(required = false) Integer contentTypeId,
                                         @RequestParam(required = false) Integer sidoId,
                                         @RequestParam(required = false) Integer gugunId,
                                         @RequestParam(required = false, defaultValue = "1") Integer pgno) {
        Map<String, String> map = new HashMap<>();
        if (key != null) {
            if (key.equals("name")) map.put("word", word);
            else if (key.equals("type")) map.put("contentTypeId", ""+contentTypeId);
            else if (key.equals("region")) {
                map.put("sidoId", ""+sidoId);
                if (gugunId != null) map.put("gugunId", ""+gugunId);
            }
        }
        map.put("key", key);
        map.put("start", ""+((pgno-1)*PAGE_SIZE+1));
        map.put("pgsize", ""+ PAGE_SIZE);

        System.out.println(map.get("sidoId"));
        System.out.println(map.get("gugunId"));
        map.forEach((k, val) -> System.out.println(k + " " + val));
        try {
            List<GetSpotRes> spots = spotService.getSpotList(map);
            if (spots != null && !spots.isEmpty()) {
                return new ResponseEntity<List<GetSpotRes>>(spots, HttpStatus.OK);
            } else {
                return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

//    @ApiOperation(value = "관광지 검색")
//    @GetMapping("/search/{keyword}")
//    public ResponseEntity<?> searchSpot(@PathVariable() String keyword) {
//        try {
//            List<GetSpotRes> spots = spotService.searchSpot(keyword);
//            if (spots != null && !spots.isEmpty()) {
//                return new ResponseEntity<List<GetSpotRes>>(spots, HttpStatus.OK);
//            } else {
//                return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
//            }
//        } catch (Exception e) {
//            return exceptionHandling(e);
//        }
//    }

    @GetMapping("/detail")
    public ModelAndView goDetail(@RequestParam int spotId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("spot_id", spotId);
        modelAndView.setViewName("spot/detail");
        return modelAndView;
    }

    @ApiOperation(value = "관광지 상세정보 조회")
    @GetMapping("/detail/{spotId}")
    public ResponseEntity<?> getSpotDetail(@PathVariable() int spotId) {
        try {
            GetSpotRes spot = spotService.getSpot(spotId);
            if (spot != null) {
                return new ResponseEntity<GetSpotRes>(spot, HttpStatus.OK);
            } else {
                return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    private ResponseEntity<String> exceptionHandling(Exception e) {
        return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
