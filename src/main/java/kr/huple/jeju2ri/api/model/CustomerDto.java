package kr.huple.jeju2ri.api.model;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {

    private String customerId;
    private String nickname;
    private String email;
    private String gender;
    private String birthyear;
    private String thumbnailImage;
    private String profileImage;
    private String agreePush;
    private String joinMethod;
    private String snsId;
    private String state;
    private String joinDt;

    private MultipartFile image;

}
