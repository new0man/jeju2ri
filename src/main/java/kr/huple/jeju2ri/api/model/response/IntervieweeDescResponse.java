package kr.huple.jeju2ri.api.model.response;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IntervieweeDescResponse {

    private String intervieweeId;
    private String korNm;
    private String engNm;
    private String intervieweeDesc;
    private String intervieweeImageUrl;
    private String intervieweeCorp;
    private String intervieweeJob;

}
