package kr.huple.jeju2ri.api.mapper;

import kr.huple.jeju2ri.api.model.PlanDto;
import kr.huple.jeju2ri.api.model.response.PlanResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PlanMapper {

    public List<PlanResponse> findByCustomerId(String customerId);
    public PlanResponse findByPlanId(String planId);
    public String getPlanId();
    public void add(PlanDto plan);
    public void delete(String planId);
    public void editTitle(PlanDto planDto);
    public void editImage(PlanDto planDto);

}
