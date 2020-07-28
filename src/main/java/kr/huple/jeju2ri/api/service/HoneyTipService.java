package kr.huple.jeju2ri.api.service;

import kr.huple.jeju2ri.api.mapper.HoneyTipMapper;
import kr.huple.jeju2ri.api.model.HoneyTipDto;
import kr.huple.jeju2ri.api.model.response.HoneyTipResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HoneyTipService {

    private final HoneyTipMapper honeyTipMapper;

    public HoneyTipService(HoneyTipMapper honeyTipMapper) {
        this.honeyTipMapper = honeyTipMapper;
    }

    public List<HoneyTipResponse> findByPlaceId(String placeId) {
        return honeyTipMapper.findByPlaceId(placeId);
    }

    public HoneyTipResponse findByTipId(Long tipId) {
        return honeyTipMapper.findByTipId(tipId);
    }

    public void add(HoneyTipDto param) {
        honeyTipMapper.add(param);
    }

    public void edit(HoneyTipDto param) {
        honeyTipMapper.edit(param);
    }

    public void delete(Long tipId) {
        honeyTipMapper.delete(tipId);
    }

}
