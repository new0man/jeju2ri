package kr.huple.jeju2ri.api.service;

import kr.huple.jeju2ri.api.mapper.MyPageMapper;
import kr.huple.jeju2ri.api.model.MyPageDto;
import kr.huple.jeju2ri.api.model.response.CategoryMyPlanResponse;
import kr.huple.jeju2ri.api.model.response.MyPageMyPlanResponse;
import kr.huple.jeju2ri.api.model.response.MyPageMyPlanScheduleResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyPageService {

    private final MyPageMapper myPageMapper;

    public MyPageService(MyPageMapper myPageMapper) {
        this.myPageMapper = myPageMapper;
    }


    public List<CategoryMyPlanResponse> findPlaceByCustomerId(String customerId) {
        return myPageMapper.findPlaceByCustomerId(customerId);
    }

    public List<MyPageMyPlanResponse> findPlanByCustomerId(String customerId) {
        return myPageMapper.findPlanByCustomerId(customerId);
    }

    public List<MyPageMyPlanResponse> findCategoryPlan(MyPageDto param) {
        return myPageMapper.findCategoryPlan(param);
    }

    public List<MyPageMyPlanScheduleResponse> findCategorySchedule(MyPageDto param) {
        return myPageMapper.findCategorySchedule(param);
    }
}
