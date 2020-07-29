package kr.huple.jeju2ri.api.model.response;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpotResponse {

    private String spotId;
    private String spotNm;
    private Double spotLat;
    private Double spotLng;
    private String spotAddr;
    private String spotAddrDesc;
    private String imageUrl;
    private String spotTime;
    private String spotContents;


}
