package kr.huple.jeju2ri.api.mapper;

import kr.huple.jeju2ri.api.model.HoneyTipDto;
import kr.huple.jeju2ri.api.model.response.HoneyTipResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HoneyTipMapper {

    public List<HoneyTipResponse> findByPlaceId(String placeId);

    public HoneyTipResponse findByTipId(Long tipId);

    public void add(HoneyTipDto param);

    public void edit(HoneyTipDto param);

    public void delete(Long tipId);

}
