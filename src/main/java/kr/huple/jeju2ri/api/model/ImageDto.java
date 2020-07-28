package kr.huple.jeju2ri.api.model;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageDto {

    private String imageId;
    private String imageUrl;
    private String originalFileName;

}
