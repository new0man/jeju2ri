package kr.huple.jeju2ri.api.model.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostMainResponse {

    private PostDescResponse postDesc;
    private PlaceDescResponse placeDesc;
    private IntervieweeDescResponse intervieweeDesc;
    // 관광지 포스트
    private List<PostResponse> posts;
    // 관광지 꿀팁
    private List<HoneyTipResponse> honeyTips;
    // todo cheol
    // 쿠폰
    // 이벤트
    // 근처 식당 및 숙박
    // 코스

}
