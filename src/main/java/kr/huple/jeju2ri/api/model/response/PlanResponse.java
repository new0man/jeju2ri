package kr.huple.jeju2ri.api.model.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlanResponse {

    private String planId;
    private String title;
    private String planStartDate;
    private String planEndDate;
    private Boolean isLeader;
    private String createDt;

    private List<PlanDayResponse> planDays;

}
