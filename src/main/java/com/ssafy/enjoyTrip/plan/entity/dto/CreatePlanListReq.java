package com.ssafy.enjoyTrip.plan.entity.dto;

<<<<<<< HEAD
import io.swagger.annotations.ApiParam;
=======
>>>>>>> 2ea5055 (plan - 기능 구현 / 테스트 미완료)
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class CreatePlanListReq {
<<<<<<< HEAD
    @ApiParam(value="관광지를 추가할 계획의 ID")
    private int planId;
    @ApiParam(value="추가할 관광지 ID")
=======
    private int planId;
>>>>>>> 2ea5055 (plan - 기능 구현 / 테스트 미완료)
    private int attractionId;
}
