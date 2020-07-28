package kr.huple.jeju2ri.api.model.response;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse {

    private String customerId;
    private String nickname;
    private String email;
    private String gender;
    private String birthyear;
    private String thumbnailImage;
    private String profileImage;
    private String agreePush;
    private String joinMethod;
    private String snsId;

}
