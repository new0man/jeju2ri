package kr.huple.jeju2ri.api.model.response;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchResponse {

    private String gubun;
    private String placeId;
    private String postId;
    private String title;
    private String companionType;
    private String largeCategory;
    private String smallCategory;
    private String rpsntImageUrl;
    private String writer;
    private String createDt;

}
