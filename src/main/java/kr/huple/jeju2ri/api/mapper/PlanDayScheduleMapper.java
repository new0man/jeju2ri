package kr.huple.jeju2ri.api.mapper;

import kr.huple.jeju2ri.api.model.PlanDayScheduleDto;
import kr.huple.jeju2ri.api.model.response.PlanDayScheduleResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PlanDayScheduleMapper {

    public List<PlanDayScheduleResponse> findByPk(PlanDayScheduleDto param);
    public void delete(PlanDayScheduleDto param);
    public void add(PlanDayScheduleDto param);
    public void edit(PlanDayScheduleDto planDayScheduleDto);
    public void editOrder(PlanDayScheduleDto planDayScheduleDto);
    public List<PlanDayScheduleDto> findByPlanDay(PlanDayScheduleDto planDayScheduleDto);

}
