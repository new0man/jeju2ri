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
    private String companionTypeCd;
    private String largeCategoryCd;
    private String smallCategoryCd;
    private String rpsntImageUrl;
    private String writer;
    private String createDt;

}
