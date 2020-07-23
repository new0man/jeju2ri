package kr.huple.jeju2ri.api.model.response;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDescResponse {

    private String postId;
    private String placeId;
    private String title;
    private String subTitle;
    private String contents;
    private String rpsntImageUrl;
    private String course;

    private String intervieweeId;

}
