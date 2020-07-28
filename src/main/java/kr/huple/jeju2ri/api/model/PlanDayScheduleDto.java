package kr.huple.jeju2ri.api.model;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlanDayScheduleDto {

    private String planId;
    private Integer planSeqNo;
    private String planDate;
    private Integer scheduleSeqNo;
    private String gubun;
    private String placeId;
    private String postId;
    private String spotId;
    private Integer sortOrder;

    private String afterPlanId;
    private Integer afterPlanSeqNo;
    private String afterPlanDate;

}
