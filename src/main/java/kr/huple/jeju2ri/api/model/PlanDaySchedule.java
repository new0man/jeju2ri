package kr.huple.jeju2ri.api.model;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlanDaySchedule {

    private String planId;
    private Integer planSeqNo;
    private String planDate;
    private Integer planScheduleSeqNo;
    private String gubun;
    private String spotId;

}
