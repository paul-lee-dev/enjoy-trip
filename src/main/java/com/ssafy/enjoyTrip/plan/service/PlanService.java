package com.ssafy.enjoyTrip.plan.service;

import com.ssafy.enjoyTrip.common.BaseException;
import com.ssafy.enjoyTrip.common.constant.Scope;
import com.ssafy.enjoyTrip.plan.dao.PlanDao;
import com.ssafy.enjoyTrip.plan.entity.PlanList;
import com.ssafy.enjoyTrip.plan.entity.dto.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.*;

import static com.ssafy.enjoyTrip.common.BaseResponseStatus.*;
import static com.ssafy.enjoyTrip.common.constant.Constants.PAGE_SIZE;

@Service
public class PlanService {

    private final PlanDao planDao;

    public PlanService(PlanDao planDao) {
        this.planDao = planDao;
    }

    public List<GetPlansRes> getPlans(GetPlansReq getPlansReq) throws BaseException {
        try {
            if (getPlansReq.getPageNo() == null) getPlansReq.setPageNo(1);
            int pageNo = getPlansReq.getPageNo();
            getPlansReq.setStart((pageNo-1) * PAGE_SIZE);
            return planDao.getPlans(getPlansReq);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BaseException(DB_ERROR);
        }
    }

    public int getPageCnt() throws BaseException {
        try {
            int planCnt = planDao.getPlanCnt();
            int pageCnt = planCnt / PAGE_SIZE;
            if (planCnt % PAGE_SIZE != 0) pageCnt++;

            return pageCnt;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BaseException(DB_ERROR);
        }
    }

    public GetPlanRes getPlan(int planId, int pageNo) throws BaseException {
        try {
            GetPlanRes getPlanRes = planDao.getPlanByPlanId(planId);
            List<PlanListDto> planLists = planDao.getPlanLists(planId);
            List<List<PlanListDto>> dailyPlans = sortPlanLists(planLists, getPlanRes.getOrderString());

            if (getPlanRes != null) {
                getPlanRes.setPlanLists(dailyPlans);
                getPlanRes.setPageNo(pageNo);
            }
//            System.out.println(getPlanRes);
            return getPlanRes;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BaseException(DB_ERROR);
        }
    }

    public void createPlan(CreatePlanReq createPlanReq) throws BaseException {
        try {
            if (createPlanReq.getScope() == null) createPlanReq.setScope(Scope.PUBLIC);
            planDao.createPlan(createPlanReq);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BaseException(DB_ERROR);
        }
    }

    /**
     * 새로운 planList를 생성하고,<br>
     * 소속 plan의 orderString 마지막에 추가
     * @param createPlanListReq
     * @throws BaseException
     */
    public void createPlanList(CreatePlanListReq createPlanListReq) throws BaseException {
        try {
            planDao.createPlanList(createPlanListReq);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BaseException(DB_ERROR);
        }
    }

    /**
     * 계획 관리 페이지에서, 관광지 순서만 변경하는 작업(삭제는 별도로)
     * @param modifyOrderReq 새로운 orderString
     * @throws BaseException
     */
    public void modifyOrder(ModifyOrderReq modifyOrderReq) throws BaseException {
        try {
            planDao.modifyOrder(modifyOrderReq);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BaseException(DB_ERROR);
        }
    }

    public void deletePlan(int planId) throws BaseException {
        try {
            planDao.deletePlan(planId);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BaseException(DB_ERROR);
        }
    }

    @Transactional
    public void deletePlanList(int planListId) throws BaseException {
        try {
            ModifyOrderReq plan = planDao.getPlanUsingPlanListId(planListId);
            if (plan == null) throw new BaseException(WRONG_PLANLIST_ID);
            String orderString = plan.getOrderString();

            if (orderString.indexOf(" " + planListId) == -1) throw new BaseException(ORDERSTRING_ERROR);
            // 방문 순서 정보에서 삭제
            String newOrderString = orderString.replace(" " + planListId, ""); // orderString 생성 순서에 주의
            plan.setOrderString(newOrderString);
            planDao.modifyOrder(plan);

            // planList에서 삭제
            planDao.deletePlanList(planListId);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BaseException(DB_ERROR);
        }
    }

    /**
     * @param planLists 정렬되지 않은 planLists
     * @param orderString order 정보를 담은 문자열
     * @return 정렬된 planLists
     */
    private List<List<PlanListDto>> sortPlanLists(List<PlanListDto> planLists, String orderString) {
        int n = planLists.size();
        List<PlanListDto> sortedPlanLists = new ArrayList<>(n);
        Map<Integer, PlanListDto> mapForSorting = new HashMap<>();
        planLists.forEach((planList) -> mapForSorting.put(planList.getPlanListId(), planList));

        int lastDayNum = -1;

        StringTokenizer st = new StringTokenizer(orderString);
        for (int i = 0; i < n; i++) {
            int planListId = Integer.parseInt(st.nextToken());
            PlanListDto cur = mapForSorting.get(planListId);
            lastDayNum = Math.max(lastDayNum, cur.getDayNum());

            sortedPlanLists.add(cur);
        }
        return makeDailyPlans(sortedPlanLists, lastDayNum);
    }

    /**
     * 순서대로 정렬된 관광계획 -> 일별로 분할해서 반환
     * @param sortedPlanLists
     * @param lastDayNum
     * @return dailyPlans = List / 1-based
     */
    private List<List<PlanListDto>> makeDailyPlans(List<PlanListDto> sortedPlanLists, int lastDayNum) {
        List<List<PlanListDto>> dailyPlans = new ArrayList<>(lastDayNum+1);

        for (int i = 0; i <= lastDayNum; i++) {
            dailyPlans.add(new ArrayList<>());
        }

        sortedPlanLists.forEach((planList) -> {
            dailyPlans.get(planList.getDayNum()).add(planList);
        });

        return dailyPlans;
    }

}
