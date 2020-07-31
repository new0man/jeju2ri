package kr.huple.jeju2ri.api.mapper;

import kr.huple.jeju2ri.api.model.SpotDto;
import kr.huple.jeju2ri.api.model.response.SpotResponse;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SpotMapper {
    public String getSpotId();
    public void add(SpotDto spot);
    public SpotResponse findBySpotId(String spotId);
    public void edit(SpotDto spot);
}
