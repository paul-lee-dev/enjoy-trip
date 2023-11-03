package com.ssafy.enjoyTrip.plan;

import com.ssafy.enjoyTrip.common.BaseException;
import com.ssafy.enjoyTrip.common.BaseResponse;
import com.ssafy.enjoyTrip.plan.entity.dto.*;
import com.ssafy.enjoyTrip.plan.service.PlanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plans")
public class PlanController {

    private final PlanService planService;

    public PlanController(PlanService planService) {
        this.planService = planService;
    }

    // read

    /**
     * 계획 목록 반환
     * @param keyword, pageNo
     * @throws BaseException
     */
    @GetMapping
    public ResponseEntity<?> getPlans(@RequestParam(required = false) String keyword, @RequestParam(required = false) Integer pageNo,
                                      @RequestParam(required = false) Integer userId) throws BaseException {
        GetPlansReq getPlansReq = new GetPlansReq(keyword, pageNo, userId);
        List<GetPlansRes> plans = planService.getPlans(getPlansReq);

        return ResponseEntity
                .ok()
                .body(new BaseResponse<>(plans));
    }

    /**
     * plan 목록으로 첫 이동 시, 페이지 버튼 만들 때 쓰일 데이터
     */
    @GetMapping("/pagecnt")
    public ResponseEntity<?> getPageCnt() throws BaseException {
        return ResponseEntity
                .ok()
                .body(new BaseResponse<>(planService.getPageCnt()));
    }

    @GetMapping("/{planId}")
    public ResponseEntity<?> getPlan(@PathVariable int planId, @RequestParam int pageNo) throws BaseException {
        return ResponseEntity
                .ok()
                .body(planService.getPlan(planId, pageNo));
    }

    // create

    @PostMapping
    public ResponseEntity<?> createPlan(@RequestBody CreatePlanReq createPlanReq) throws BaseException {
        planService.createPlan(createPlanReq);
        return ResponseEntity
                .ok()
                .build();
    }

    @PostMapping("/planlist")
    public ResponseEntity<?> createPlanList(@RequestBody CreatePlanListReq createPlanListReq) throws BaseException {
        planService.createPlanList(createPlanListReq);
        return ResponseEntity
                .ok()
                .build();
    }

    // update

    @PatchMapping
    public ResponseEntity<?> modifyOrder(@RequestBody ModifyOrderReq modifyOrderReq) throws BaseException {
        planService.modifyOrder(modifyOrderReq);
        return ResponseEntity
                .ok()
                .build();
    }

    // delete
    @DeleteMapping("/{planId}")
    public ResponseEntity<?> deletePlan(@PathVariable int planId) throws BaseException {
        planService.deletePlan(planId);
        return ResponseEntity
                .ok()
                .build();
    }

    @DeleteMapping("/planlist/{planlistId}")
    public ResponseEntity<?> deletePlanList(@PathVariable int planListId) throws BaseException {
        planService.deletePlanList(planListId);
        return ResponseEntity
                .ok()
                .build();
    }
}
