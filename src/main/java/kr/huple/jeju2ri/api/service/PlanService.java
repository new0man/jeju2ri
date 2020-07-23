package kr.huple.jeju2ri.api.service;

import kr.huple.jeju2ri.api.mapper.PlanMapper;
import kr.huple.jeju2ri.api.model.Plan;
import kr.huple.jeju2ri.api.model.response.PlanResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanService {

    private final PlanMapper planMapper;

    public PlanService(PlanMapper planMapper) {
        this.planMapper = planMapper;
    }

    public List<PlanResponse> findByCustomerId(String customerId) {
        return planMapper.findByCustomerId(customerId);
    }

    public PlanResponse findByPlanId(String planId) {
        return planMapper.findByPlanId(planId);
    }

    public String getPlanId() { return planMapper.getPlanId(); }

    public void add(Plan plan) { planMapper.add(plan); }

    public void delete(String planId) { planMapper.delete(planId); }
}
