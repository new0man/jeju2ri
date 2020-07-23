package kr.huple.jeju2ri.api.service;

import kr.huple.jeju2ri.api.mapper.PlaceMapper;
import kr.huple.jeju2ri.api.model.response.PlaceCategoryResponse;
import kr.huple.jeju2ri.api.model.response.PlaceDescResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceService {

    private final PlaceMapper placeMapper;

    public PlaceService(PlaceMapper placeMapper) {
        this.placeMapper = placeMapper;
    }

    public List<PlaceDescResponse> findAll() {
        return placeMapper.findAll();
    }

    public PlaceDescResponse findByPlaceId(String placeId) {
        return placeMapper.findByPlaceId(placeId);
    }

    public List<PlaceCategoryResponse> getPlaceCategory(String placeId) {
        return placeMapper.getPlaceCategory(placeId);
    }
}
