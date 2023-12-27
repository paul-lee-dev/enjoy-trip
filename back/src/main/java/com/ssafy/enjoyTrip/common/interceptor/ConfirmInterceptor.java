//package com.ssafy.enjoyTrip.common.interceptor;
//
//import com.ssafy.enjoyTrip.common.BaseException;
//import com.ssafy.enjoyTrip.common.BaseResponseStatus;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//public class ConfirmInterceptor implements HandlerInterceptor {
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        HttpSession session = request.getSession();
//        Object userObj = session.getAttribute("loginUser");
//
//        if (userObj == null) {
//            throw new BaseException(BaseResponseStatus.NEED_LOGIN);
//        }
//        return true;
//    }
//}
