package kr.huple.jeju2ri.api.model.response;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostResponse {

    private String postId;
    private String title;
    private String subTitle;
    private String companionType;
    private String rpsntImageUrl;

}
