package kr.huple.jeju2ri.api.model.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlanDayResponse {

    private String planId;
    private Integer planSeqNo;
    private String planDate;

    private List<PlanDayScheduleResponse> planDaySchedules;

}
