package kr.huple.jeju2ri.api.mapper;

import kr.huple.jeju2ri.api.model.Spot;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SpotMapper {

    public String getSpotId();

    public void add(Spot spot);

}
