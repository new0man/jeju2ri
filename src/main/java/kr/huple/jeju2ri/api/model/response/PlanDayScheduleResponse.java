package kr.huple.jeju2ri.api.model.response;

import lombok.*;

import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlanDayScheduleResponse {

    private String scheduleSeqNo;
    private String gubun;
    private String placeId;
    private String postId;
    private String spotId;
    private String rpsntImageUrl;
    private String placeNm;
    private String title;
    private Integer leadTime;
    private String spotTime;
    private Integer sortOrder;

    private List<PlaceCategoryResponse> categories;

}
