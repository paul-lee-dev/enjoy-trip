package com.ssafy.enjoyTrip.plan.service;

import com.ssafy.enjoyTrip.common.BaseException;
import com.ssafy.enjoyTrip.plan.dao.PlanDao;
import com.ssafy.enjoyTrip.plan.entity.dto.*;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.*;

import static com.ssafy.enjoyTrip.common.BaseResponseStatus.DB_ERROR;
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
            GetPlanRes getPlanRes = planDao.getPlan(planId);
            if (getPlanRes != null) {
                getPlanRes.setPlanlists(sortPlanLists(getPlanRes.getPlanlists(), getPlanRes.getOrderString()));
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
            planDao.createPlan(createPlanReq);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BaseException(DB_ERROR);
        }
    }

    /**
     * 새로운 planlist를 생성하고,<br>
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

    public void deletePlanList(int planListId) throws BaseException {
        try {
            planDao.deletePlanList(planListId);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BaseException(DB_ERROR);
        }
    }

    /**
     * @param planlists 정렬되지 않은 planlists
     * @param orderString order 정보를 담은 문자열
     * @return 정렬된 planlists
     */
    private List<PlanListDto> sortPlanLists(List<PlanListDto> planlists, String orderString) {
        int n = planlists.size();
        List<PlanListDto> result = new ArrayList<>(n);
        Map<Integer, PlanListDto> mapForSorting = new HashMap<>();
        planlists.forEach((planlist) -> mapForSorting.put(planlist.getPlanListId(), planlist));

        StringTokenizer st = new StringTokenizer(orderString);
        for (int i = 0; i < n; i++) {
            int planlistId = Integer.parseInt(st.nextToken());
            result.add(mapForSorting.get(planlistId));
        }
        return result;
    }

}
