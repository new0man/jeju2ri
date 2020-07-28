package kr.huple.jeju2ri.api.service;

import kr.huple.jeju2ri.api.mapper.PlanDayScheduleMapper;
import kr.huple.jeju2ri.api.model.PlanDayScheduleDto;
import kr.huple.jeju2ri.api.model.response.PlanDayScheduleResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanDayScheduleService {

    private final PlanDayScheduleMapper planDayScheduleMapper;

    public PlanDayScheduleService(PlanDayScheduleMapper planDayScheduleMapper) {
        this.planDayScheduleMapper = planDayScheduleMapper;
    }

    public List<PlanDayScheduleResponse> findByPk(PlanDayScheduleDto param) {
        return planDayScheduleMapper.findByPk(param);
    }

    public void delete(PlanDayScheduleDto param) {
        planDayScheduleMapper.delete(param);
    }

    public void add(PlanDayScheduleDto param) {
        planDayScheduleMapper.add(param);
    }

    /**
     * Plan Schedule 이동
     * @param planDayScheduleDto
     */
    public void edit(PlanDayScheduleDto planDayScheduleDto) {
        planDayScheduleMapper.edit(planDayScheduleDto);
    }

    public void editOrder(PlanDayScheduleDto planDayScheduleDto) {
        planDayScheduleMapper.editOrder(planDayScheduleDto);
    }

    public List<PlanDayScheduleDto> findByPlanDay(PlanDayScheduleDto planDayScheduleDto) {
        return planDayScheduleMapper.findByPlanDay(planDayScheduleDto);
    }
}
