package kr.huple.jeju2ri.api.model.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MyPageMyPlanResponse {

    private String planId;
    private String title;
    private String planStartDate;
    private String planEndDate;
    private Integer memberCnt;

    private List<MyPageMyPlanScheduleResponse> myPlanSchedules;

}
