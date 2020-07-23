package kr.huple.jeju2ri.api.service;

import kr.huple.jeju2ri.api.mapper.IntervieweeMapper;
import kr.huple.jeju2ri.api.model.response.IntervieweeDescResponse;
import org.springframework.stereotype.Service;

@Service
public class IntervieweeService {

    private final IntervieweeMapper intervieweeMapper;

    public IntervieweeService(IntervieweeMapper intervieweeMapper) {
        this.intervieweeMapper = intervieweeMapper;
    }

    public IntervieweeDescResponse findByIntervieweeId(String intervieweeId) {
        return intervieweeMapper.findByIntervieweeId(intervieweeId);
    }

}
