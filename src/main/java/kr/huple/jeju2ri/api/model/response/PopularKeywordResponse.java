package kr.huple.jeju2ri.api.model.response;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PopularKeywordResponse {

    private Integer seqNo;
    private String placeId;
    private String placeNm;

}
