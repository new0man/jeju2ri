package kr.huple.jeju2ri.api.mapper;

import kr.huple.jeju2ri.api.model.response.PostDescResponse;
import kr.huple.jeju2ri.api.model.response.PostResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {

    public PostDescResponse findByPostId(String postId);

    public List<PostResponse> findByPlaceId(String placeId);

}
