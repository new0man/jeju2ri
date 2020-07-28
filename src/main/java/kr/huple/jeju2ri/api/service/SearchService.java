package kr.huple.jeju2ri.api.service;

import kr.huple.jeju2ri.api.mapper.SearchMapper;
import kr.huple.jeju2ri.api.model.SearchDto;
import kr.huple.jeju2ri.api.model.response.SearchResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {

    private final SearchMapper searchMapper;

    public SearchService(SearchMapper searchMapper) {
        this.searchMapper = searchMapper;
    }

    public List<SearchResponse> findByKeyword(SearchDto param) {
        return searchMapper.findByKeyword(param);
    }

    public List<SearchResponse> findByPlaceId(String placeId) {
        return searchMapper.findByPlaceId(placeId);
    }

}
