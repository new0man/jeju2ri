package kr.huple.jeju2ri.api.model.response;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HomePostResponse {

    private String gubun;

    private String postId;
    private String placeId;
    private String rpsntImageUrl;
    private String title;
    private String subTitle;
    private String companionType;
    private String writer;
    private String writeDate;

    private String placeNm;

}
