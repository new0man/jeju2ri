package kr.huple.jeju2ri.api.model;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlanMember {

    private String planId;
    private String customerId;
    private String auth;

    private String beforeLeaderCustomerId;
    private String afterLeaderCustomerId;

}
