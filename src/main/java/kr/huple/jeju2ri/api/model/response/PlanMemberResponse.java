package kr.huple.jeju2ri.api.model.response;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlanMemberResponse {

    private String planId;
    private Integer seqNo;
    private String customerId;
    private String auth;
    private String nickname;
    private String joinMethod;
    private String thumbnailImage;
    private String profileImage;

}
