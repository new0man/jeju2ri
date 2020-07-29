package kr.huple.jeju2ri.api.controller;

import kr.huple.jeju2ri.api.service.SpotService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/spots")
public class SpotController {

    private final SpotService spotService;

    public SpotController(SpotService spotService) {
        this.spotService = spotService;
    }

    @GetMapping("/{spotId}")
    public Object findBySpotId(@PathVariable("spotId") String spotId) throws Exception {
        return spotService.findBySpotId(spotId);
    }

}
