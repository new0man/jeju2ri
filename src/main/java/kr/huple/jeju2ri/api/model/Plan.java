package kr.huple.jeju2ri.api.model;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Plan {

    private String planId;
    private String title;
    private String companionTypeCd;
    private String planStartDate;
    private String planEndDate;
    private String createId;

}
