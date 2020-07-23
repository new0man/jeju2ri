package kr.huple.jeju2ri.api.service;

import kr.huple.jeju2ri.api.mapper.PlanMemberMapper;
import kr.huple.jeju2ri.api.model.PlanMember;
import kr.huple.jeju2ri.api.model.response.PlanMemberResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanMemberService {

    private final PlanMemberMapper planMemberMapper;

    public PlanMemberService(PlanMemberMapper planMemberMapper) {
        this.planMemberMapper = planMemberMapper;
    }

    public List<PlanMemberResponse> findByPlanId(String planId) {
        return planMemberMapper.findByPlanId(planId);
    }

    public void add(PlanMember paramPlanMember) { planMemberMapper.add(paramPlanMember); }

    public void delete(PlanMember planMember) {
        planMemberMapper.delete(planMember);
    }

    public void edit(PlanMember planMember) {
        planMemberMapper.edit(planMember);
    }
}
