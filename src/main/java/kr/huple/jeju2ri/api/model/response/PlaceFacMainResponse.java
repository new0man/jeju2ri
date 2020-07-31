package kr.huple.jeju2ri.api.model.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaceFacMainResponse {

    private String homepage;
    private String phone;
    private String facebook;
    private String instagram;

    private String imageId;

    private List<PlaceFacInfoResponse> placeFacInfos;
    private List<PlaceFacInfoResponse> placeBarrierFreeFacInfos;
    private List<ImageResponse> placeFacImages;

}
