package kr.huple.jeju2ri.api.model;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HoneyTip {

    private Integer tipId;
    private String placeId;
    private String title;
    private String addr;
    private String aliasNm;
    private String contents;
    private Double tipLat;
    private Double tipLng;
    private Integer hitCnt;
    private Integer favoriteCnt;
    private String state;
    private String createId;
    private String createDt;
    private String updateId;
    private String updateDt;

}
