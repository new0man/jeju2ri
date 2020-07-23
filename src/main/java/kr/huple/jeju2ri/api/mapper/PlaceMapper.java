package kr.huple.jeju2ri.api.mapper;

import kr.huple.jeju2ri.api.model.response.PlaceCategoryResponse;
import kr.huple.jeju2ri.api.model.response.PlaceDescResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PlaceMapper {

    public PlaceDescResponse findByPlaceId(String placeId);

    public List<PlaceDescResponse> findAll();

    List<PlaceCategoryResponse> getPlaceCategory(String placeId);

}
