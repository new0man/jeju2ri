package kr.huple.jeju2ri.api.mapper;

import kr.huple.jeju2ri.api.model.SpotDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SpotMapper {

    public String getSpotId();

    public void add(SpotDto spot);

}
