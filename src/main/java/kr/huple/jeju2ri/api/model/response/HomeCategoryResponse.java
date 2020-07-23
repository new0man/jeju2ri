package kr.huple.jeju2ri.api.model.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HomeCategoryResponse {

    private String category;
    private String htmlColorCode;
    private List<HomePostResponse> bestPosts;
    private List<HomePostResponse> posts;

}
