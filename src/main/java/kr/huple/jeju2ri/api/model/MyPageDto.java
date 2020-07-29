package kr.huple.jeju2ri.api.model;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MyPageDto {

    private String largeCategoryCd;
    private String customerId;

    private String planId;

}
