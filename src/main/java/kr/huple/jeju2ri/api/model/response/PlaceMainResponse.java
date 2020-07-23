package kr.huple.jeju2ri.api.model.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaceMainResponse {

    // 관광지 정보
    private PlaceDescResponse placeDesc;
    // 관광지 포스트
    private List<PostResponse> posts;
    // 관광지 꿀팁
    private List<HoneyTipResponse> honeyTips;

    // todo cheol
    // 근처 식당 및 숙박
    // 코스

}
