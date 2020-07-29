package kr.huple.jeju2ri.api.model.response;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MyPageMyPlanScheduleResponse {

    private Integer planSeqNo;
    private Integer scheduleSeqNo;
    private String title;

}
