package kr.huple.jeju2ri.api.service;

import kr.huple.jeju2ri.api.mapper.PlaceMapper;
import kr.huple.jeju2ri.api.model.response.*;
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

    public PlaceFacMainResponse findPlaceFacByPlaceId(String placeId) {
        return placeMapper.findPlaceFacByPlaceId(placeId);
    }

    public List<PlaceFacInfoResponse> findPlaceFacInfoByPlaceId(String placeId) {
        return placeMapper.findPlaceFacInfoByPlaceId(placeId);
    }

    public List<PlaceFacInfoResponse> findPlaceBarrierFreeFacInfoByPlaceId(String placeId) {
        return placeMapper.findPlaceBarrierFreeFacInfoByPlaceId(placeId);
    }

}
