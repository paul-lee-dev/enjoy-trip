package com.ssafy.enjoyTrip.spot;


import com.ssafy.enjoyTrip.spot.entity.Spot;
import com.ssafy.enjoyTrip.spot.entity.dto.GetSpotRes;
import com.ssafy.enjoyTrip.spot.service.SpotService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(value = "*")
@RequestMapping("/spot")
public class SpotController {
    private final SpotService spotService;

    public SpotController(SpotService spotService) {
        this.spotService = spotService;
    }

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

    @GetMapping("/{sidoId}/{gugunid}")
    public ResponseEntity<?> getSido(@PathVariable() int sidoId, @PathVariable() int gugunId) {
        Map<String, Integer> map = new HashMap<>();
        map.put("sidoId", sidoId);
        map.put("gugunId", gugunId);
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

    @GetMapping("/search/{keyword}")
    public ResponseEntity<?> searchSpot(@PathVariable() String keyword) {
        try {
            List<GetSpotRes> spots = spotService.searchSpot(keyword);
            if (spots != null && !spots.isEmpty()) {
                return new ResponseEntity<List<GetSpotRes>>(spots, HttpStatus.OK);
            } else {
                return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @GetMapping("/detail")
    public ModelAndView goDetail(@RequestParam int spotId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("spot_id", spotId);
        modelAndView.setViewName("spot/detail");
        return modelAndView;
    }

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
