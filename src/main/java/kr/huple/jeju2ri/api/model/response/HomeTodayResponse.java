package kr.huple.jeju2ri.api.model.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HomeTodayResponse {

    // TOP10 포스트
    private List<HomePostResponse> posts;

    // 무장애 여행 포스트
    private HomePostResponse BarrierFreePost;

    // todo
    // 추천 코스 - 코스피드
    // 이벤트
    // 인터뷰이 추천 장소
    // 꿀팁

}
