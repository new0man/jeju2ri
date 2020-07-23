package kr.huple.jeju2ri.api.mapper;

import kr.huple.jeju2ri.api.model.PlanDay;
import kr.huple.jeju2ri.api.model.response.PlanDayResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PlanDayMapper {

    public List<PlanDayResponse> findByPlanId(String planId);

    public void add(PlanDay planDay);

}
