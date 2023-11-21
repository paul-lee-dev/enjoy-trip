package com.ssafy.enjoyTrip.plan.dao;

import com.ssafy.enjoyTrip.plan.entity.dto.*;

import java.sql.SQLException;
import java.util.List;

public interface PlanDao {
    List<GetPlansRes> getPlans(GetPlansReq getPlansReq) throws SQLException;
    int getPlanCnt() throws SQLException;
    GetPlanRes getPlanByPlanId(int planId) throws SQLException;
    ModifyOrderReq getPlanUsingPlanListId(int planId) throws SQLException;
    List<PlanListDto> getPlanLists(int planId) throws SQLException;
    void createPlan(CreatePlanReq createPlanReq) throws SQLException;
    void createPlanList(CreatePlanListReq createPlanListReq) throws SQLException;
    void modifyOrder(ModifyOrderReq modifyOrderReq) throws SQLException;
    void deletePlan(int planId) throws SQLException;
    void deletePlanList(int planListId) throws SQLException;
}
