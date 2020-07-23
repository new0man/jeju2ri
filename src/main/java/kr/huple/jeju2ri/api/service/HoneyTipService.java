package kr.huple.jeju2ri.api.service;

import kr.huple.jeju2ri.api.mapper.HoneyTipMapper;
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

}
