package kr.huple.jeju2ri.api.mapper;

import kr.huple.jeju2ri.api.model.PlanMember;
import kr.huple.jeju2ri.api.model.response.PlanMemberResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PlanMemberMapper {

    public List<PlanMemberResponse> findByPlanId(String planId);

    public void add(PlanMember paramPlanMember);

    public void delete(PlanMember planMember);

    public void edit(PlanMember planMember);

}
