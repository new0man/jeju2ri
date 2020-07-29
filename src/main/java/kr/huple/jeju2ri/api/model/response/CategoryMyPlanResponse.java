package kr.huple.jeju2ri.api.model.response;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryMyPlanResponse {

    private String largeCategoryCd;
    private String largeCategory;
    private Integer placeCount;

}
