package kr.huple.jeju2ri.api.mapper;

import kr.huple.jeju2ri.api.model.Plan;
import kr.huple.jeju2ri.api.model.PlanDaySchedule;
import kr.huple.jeju2ri.api.model.response.PlanDayScheduleResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PlanDayScheduleMapper {

    public List<PlanDayScheduleResponse> findByPk(PlanDaySchedule param);
    public void delete(PlanDaySchedule param);
    public void add(PlanDaySchedule param);

}
