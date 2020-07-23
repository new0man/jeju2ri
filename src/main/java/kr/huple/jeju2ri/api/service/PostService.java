package kr.huple.jeju2ri.api.service;

import kr.huple.jeju2ri.api.mapper.PostMapper;
import kr.huple.jeju2ri.api.model.response.PostDescResponse;
import kr.huple.jeju2ri.api.model.response.PostResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostMapper postMapper;

    public PostService(PostMapper postMapper) {
        this.postMapper = postMapper;
    }

    public PostDescResponse findByPostId(String postId) {
        return postMapper.findByPostId(postId);
    }

    public List<PostResponse> findByPlaceId(String placeId) {
        return postMapper.findByPlaceId(placeId);
    }
}
