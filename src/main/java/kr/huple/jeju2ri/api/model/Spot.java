package kr.huple.jeju2ri.api.model;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Spot {

    private String planId;
    private Integer planSeqNo;
    private String gubun;
    private String planDate;

    private String spotId;
    private String spotNm;
    private String spotLat;
    private String spotLng;
    private String spotTime;
    private String spotAddr;
    private String spotAddrDesc;
    private String imageUrl;
    private String createId;
    private String updateId;

    private MultipartFile photo;

}
