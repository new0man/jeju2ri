package kr.huple.jeju2ri.api.mapper;

import kr.huple.jeju2ri.api.model.response.PopularKeywordResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PopularKeywordMapper {

    public List<PopularKeywordResponse> findAll();

}
