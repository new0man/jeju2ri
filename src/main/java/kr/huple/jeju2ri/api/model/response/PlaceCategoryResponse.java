package kr.huple.jeju2ri.api.model.response;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaceCategoryResponse {

    private Integer seqNo;
    private String largeCategory;
    private String smallCategory;

}
