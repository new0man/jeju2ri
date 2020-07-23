package kr.huple.jeju2ri.api.service;

import kr.huple.jeju2ri.api.mapper.PlanDayScheduleMapper;
import kr.huple.jeju2ri.api.model.Plan;
import kr.huple.jeju2ri.api.model.PlanDaySchedule;
import kr.huple.jeju2ri.api.model.PlanMember;
import kr.huple.jeju2ri.api.model.response.PlanDayScheduleResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanDayScheduleService {

    private final PlanDayScheduleMapper planDayScheduleMapper;

    public PlanDayScheduleService(PlanDayScheduleMapper planDayScheduleMapper) {
        this.planDayScheduleMapper = planDayScheduleMapper;
    }

    public List<PlanDayScheduleResponse> findByPk(PlanDaySchedule param) {
        return planDayScheduleMapper.findByPk(param);
    }

    public void delete(PlanDaySchedule param) {
        planDayScheduleMapper.delete(param);
    }

    public void add(PlanDaySchedule param) {
        planDayScheduleMapper.add(param);
    }
}
