package kr.huple.jeju2ri.api.mapper;

import kr.huple.jeju2ri.api.model.response.HoneyTipResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HoneyTipMapper {

    public List<HoneyTipResponse> findByPlaceId(String placeId);

}
