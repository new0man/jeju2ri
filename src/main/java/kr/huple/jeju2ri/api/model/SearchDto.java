package kr.huple.jeju2ri.api.model;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchDto {

    private String[] locations;
    private String keyword;

}
