package kr.huple.jeju2ri.api.model.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HoneyTipResponse {

    private Integer tipId;
    private String title;
    private String contents;
    private String writerImageUrl;
    private String nickname;
    private String writeDate;
    private Integer favoriteCnt;

    private String imageId;

    private List<ImageResponse> images;

}
