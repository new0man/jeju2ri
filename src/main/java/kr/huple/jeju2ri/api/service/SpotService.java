package kr.huple.jeju2ri.api.service;

import kr.huple.jeju2ri.api.mapper.SpotMapper;
import kr.huple.jeju2ri.api.model.SpotDto;
import org.springframework.stereotype.Service;

@Service
public class SpotService {

    private final SpotMapper spotMapper;

    public SpotService(SpotMapper spotMapper) {
        this.spotMapper = spotMapper;
    }

    public String getSpotId() {
        return spotMapper.getSpotId();
    }

    public void add(SpotDto spot) {
        spotMapper.add(spot);
    }

}
