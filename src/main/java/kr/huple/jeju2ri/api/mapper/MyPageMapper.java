package kr.huple.jeju2ri.api.mapper;

import kr.huple.jeju2ri.api.model.MyPageDto;
import kr.huple.jeju2ri.api.model.response.CategoryMyPlanResponse;
import kr.huple.jeju2ri.api.model.response.MyPageMyPlanResponse;
import kr.huple.jeju2ri.api.model.response.MyPageMyPlanScheduleResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MyPageMapper {
    public List<CategoryMyPlanResponse> findPlaceByCustomerId(String customerId);
    public List<MyPageMyPlanResponse> findPlanByCustomerId(String customerId);
    public List<MyPageMyPlanResponse> findCategoryPlan(MyPageDto param);
    public List<MyPageMyPlanScheduleResponse> findCategorySchedule(MyPageDto param);
}
