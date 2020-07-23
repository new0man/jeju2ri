package kr.huple.jeju2ri.api.controller;

import kr.huple.jeju2ri.api.service.PlaceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/map")
public class MapController {

    private final PlaceService placeService;

    public MapController(PlaceService placeService) {
        this.placeService = placeService;
    }

}
