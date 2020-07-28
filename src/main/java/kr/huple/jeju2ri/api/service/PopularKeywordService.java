package kr.huple.jeju2ri.api.service;

import kr.huple.jeju2ri.api.mapper.PopularKeywordMapper;
import kr.huple.jeju2ri.api.model.response.PopularKeywordResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PopularKeywordService {

    private final PopularKeywordMapper popularKeywordMapper;

    public PopularKeywordService(PopularKeywordMapper popularKeywordMapper) {
        this.popularKeywordMapper = popularKeywordMapper;
    }

    public List<PopularKeywordResponse> findAll() {
        return popularKeywordMapper.findAll();
    }

}
