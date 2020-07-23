package kr.huple.jeju2ri.api.model.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlanMainResponse {

    private PlanResponse plan;
    private List<PlanDayResponse> planDays;
    private List<PlanMemberResponse> planMembers;

}
