package kr.huple.jeju2ri.api.model.response;

import lombok.*;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaceDescResponse {

    private String placeId;
    private String placeNm;
    private String placeOutline;
    private String placeDesc;
    private String placeFacInfo;
    private String rpsntImageUrl;
    // 위경도
    private Double placeLat;
    private Double placeLng;
    // Location
    private String largeLocation;
    private String smallLocation;
    // Category
    private List<PlaceCategoryResponse> categories;

}
