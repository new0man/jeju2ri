package kr.huple.jeju2ri.api.mapper;

import kr.huple.jeju2ri.api.model.SearchDto;
import kr.huple.jeju2ri.api.model.response.SearchResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SearchMapper {

    public List<SearchResponse> findByKeyword(SearchDto param);
    public List<SearchResponse> findByPlaceId(String placeId);

}
