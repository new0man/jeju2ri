package kr.huple.jeju2ri.api.model;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlanDto {

    private String planId;
    private String title;
    private String rpsntImageUrl;
    private String companionTypeCd;
    private String planStartDate;
    private String planEndDate;
    private String createId;
    private String updateId;

    private MultipartFile image;

}
