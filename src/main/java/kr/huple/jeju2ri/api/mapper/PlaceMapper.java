package kr.huple.jeju2ri.api.mapper;

import kr.huple.jeju2ri.api.model.response.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PlaceMapper {

    public PlaceDescResponse findByPlaceId(String placeId);

    public List<PlaceDescResponse> findAll();

    List<PlaceCategoryResponse> getPlaceCategory(String placeId);

    public PlaceFacMainResponse findPlaceFacByPlaceId(String placeId);

    public List<PlaceFacInfoResponse> findPlaceFacInfoByPlaceId(String placeId);

    public List<PlaceFacInfoResponse> findPlaceBarrierFreeFacInfoByPlaceId(String placeId);

}
