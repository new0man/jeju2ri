package kr.huple.jeju2ri.api.service;

import kr.huple.jeju2ri.api.mapper.PlanDayMapper;
import kr.huple.jeju2ri.api.model.PlanDay;
import kr.huple.jeju2ri.api.model.response.PlanDayResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanDayService {

    private final PlanDayMapper planDayMapper;

    public PlanDayService(PlanDayMapper planDayMapper) {
        this.planDayMapper = planDayMapper;
    }

    public List<PlanDayResponse> findByPlanId(String planId) {
        return planDayMapper.findByPlanId(planId);
    }

    public void add(PlanDay planDay) { planDayMapper.add(planDay); }

}
