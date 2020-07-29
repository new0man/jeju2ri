package kr.huple.jeju2ri.api.model.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MyPageResponse {

    private List<CategoryMyPlanResponse> categoryMyPlans;
    private List<MyPageMyPlanResponse> myPageMyPlans;

}
