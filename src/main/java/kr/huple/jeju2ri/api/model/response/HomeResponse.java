package kr.huple.jeju2ri.api.model.response;


import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HomeResponse {

    private HomeTodayResponse today;
    private List<HomeCategoryResponse> categories = new ArrayList<>();

}
