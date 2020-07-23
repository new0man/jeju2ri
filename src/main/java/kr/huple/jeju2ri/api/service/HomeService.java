package kr.huple.jeju2ri.api.service;

import kr.huple.jeju2ri.api.mapper.HomeMapper;
import kr.huple.jeju2ri.api.model.response.HomePostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeService {

    private final HomeMapper homeMapper;

    public HomeService(HomeMapper homeMapper) {
        this.homeMapper = homeMapper;
    }

    public List<HomePostResponse> top10Post() {
        return homeMapper.top10Post();
    }

    public HomePostResponse barrierFreePost() {
        return homeMapper.barrierFreePost();
    }

    public List<HomePostResponse> bestPostByCategory(String cd) {
        return homeMapper.bestPostByCategory(cd);
    }

    public List<HomePostResponse> postByCategory(String cd) {
        return homeMapper.postByCategory(cd);
    }

}
