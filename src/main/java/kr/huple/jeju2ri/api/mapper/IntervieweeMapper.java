package kr.huple.jeju2ri.api.mapper;

import kr.huple.jeju2ri.api.model.response.IntervieweeDescResponse;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IntervieweeMapper {

    public IntervieweeDescResponse findByIntervieweeId(String intervieweeId);

}
