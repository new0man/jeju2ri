package kr.huple.jeju2ri.api.mapper;

import kr.huple.jeju2ri.api.model.response.HomePostResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HomeMapper {

    public List<HomePostResponse> top10Post();
    public HomePostResponse barrierFreePost();
    public List<HomePostResponse> bestPostByCategory(String cd);
    public List<HomePostResponse> postByCategory(String cd);

}
