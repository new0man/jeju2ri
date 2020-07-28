package kr.huple.jeju2ri.api.model;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlanDayDto {

    private String planId;
    private String planDate;

}
