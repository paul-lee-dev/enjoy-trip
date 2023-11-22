package com.ssafy.enjoyTrip.plan;

import com.ssafy.enjoyTrip.common.BaseException;
import com.ssafy.enjoyTrip.common.BaseResponse;
import com.ssafy.enjoyTrip.plan.entity.dto.*;
import com.ssafy.enjoyTrip.plan.service.PlanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value="Plan API")
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
    @ApiOperation(value="여행계획 목록 반환")
    @GetMapping
    public ResponseEntity<?> getPlans(@ApiParam(value = "검색어") @RequestParam(required = false) String keyword,
                                      @ApiParam(value = "페이지 번호") @RequestParam(required = false) Integer pageNo,
                                      @ApiParam(value = "'내 계획 보기' 시 필요한 유저 id") @RequestParam(required = false) Integer userId) throws BaseException {
        GetPlansReq getPlansReq = new GetPlansReq(keyword, pageNo, userId);
        List<GetPlansRes> plans = planService.getPlans(getPlansReq);

        return ResponseEntity
                .ok()
                .body(new BaseResponse<>(plans));
    }

    /**
     * plan 목록으로 첫 이동 시, 페이지 버튼 만들 때 쓰일 데이터
     */
    @ApiOperation(value="plan 목록으로 첫 이동 시, 페이지 버튼 생성 때 쓸 데이터(페이지 수) 반환")
    @GetMapping("/pagecnt")
    public ResponseEntity<?> getPageCnt() throws BaseException {
        return ResponseEntity
                .ok()
                .body(new BaseResponse<>(planService.getPageCnt()));
    }

    @ApiOperation(value="여행계획 상세조회")
    @GetMapping("/{planId}")
    public ResponseEntity<?> getPlan(@PathVariable int planId, @RequestParam int pageNo) throws BaseException {
        return ResponseEntity
                .ok()
                .body(new BaseResponse<>(planService.getPlan(planId, pageNo)));
    }

    // create

    @ApiOperation(value="계획 생성")
    @PostMapping
    public ResponseEntity<?> createPlan(@RequestBody CreatePlanReq createPlanReq) throws BaseException {
        planService.createPlan(createPlanReq);
        return ResponseEntity
                .ok()
                .build();
    }

    @ApiOperation(value="다른 계획 복사하기 / userId, targetPlanId, scope 필요")
    @PostMapping("/copy")
    public ResponseEntity<?> copyPlan(@RequestBody CopyPlanReq copyPlanReq) throws BaseException {
        planService.copyPlan(copyPlanReq);
        return ResponseEntity
                .ok()
                .build();
    }

    @ApiOperation(value="관광지 추가")
    @PostMapping("/planlists")
    public ResponseEntity<?> createPlanList(@RequestBody CreatePlanListReq createPlanListReq) throws BaseException {
        planService.createPlanList(createPlanListReq);
        return ResponseEntity
                .ok()
                .build();
    }

    // update
    @ApiOperation(value="여행 계획 - 방문할 관광지 순서 수정")
    @PatchMapping
    public ResponseEntity<?> modifyOrder(@RequestBody ModifyOrderReq modifyOrderReq) throws BaseException {
        planService.modifyOrder(modifyOrderReq);
        return ResponseEntity
                .ok()
                .build();
    }

    // delete
    @ApiOperation(value="여행 계획 삭제")
    @DeleteMapping("/{planId}")
    public ResponseEntity<?> deletePlan(@PathVariable int planId) throws BaseException {
        planService.deletePlan(planId);
        return ResponseEntity
                .ok()
                .build();
    }

    @ApiOperation(value="계획에 담긴 관광지(planlist) 삭제")
    @DeleteMapping("/planlists/{planListId}")
    public ResponseEntity<?> deletePlanList(@PathVariable int planListId) throws BaseException {
        planService.deletePlanList(planListId);
        return ResponseEntity
                .ok()
                .build();
    }
}
