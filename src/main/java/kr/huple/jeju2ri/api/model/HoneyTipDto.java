package kr.huple.jeju2ri.api.model;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HoneyTipDto {

    private Long tipId;
    private String placeId;
    private String title;
    private String addr;
    private String aliasNm;
    private String contents;
    private Double tipLat;
    private Double tipLng;
    private String imageId;
    private Integer hitCnt;
    private Integer favoriteCnt;
    private String state;
    private String createId;
    private String createDt;
    private String updateId;
    private String updateDt;

    private List<ImageDto> images;

}
