package kr.huple.jeju2ri.api.controller;

import kr.huple.jeju2ri.api.model.FileDto;
import kr.huple.jeju2ri.api.model.SpotDto;
import kr.huple.jeju2ri.api.service.SpotService;
import kr.huple.jeju2ri.configuration.response.RestResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/spots")
public class SpotController {

    private final SpotService spotService;
    private final FileUploadController fileUpload;

    public SpotController(SpotService spotService, FileUploadController fileUpload) {
        this.spotService = spotService;
        this.fileUpload = fileUpload;
    }

    @GetMapping("/{spotId}")
    public Object findBySpotId(@PathVariable("spotId") String spotId) throws Exception {
        return spotService.findBySpotId(spotId);
    }

    @PutMapping(value = "/{spotId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Object findBySpotId(@PathVariable("spotId") String spotId, SpotDto spot) throws Exception {
        spot.setSpotId(spotId);
        FileDto fileDto = new FileDto();
        if(spot.getImage() != null &&
                spot.getImage().getOriginalFilename() != null &&
                !"".equals(spot.getImage().getOriginalFilename())) {
            RestResponse<?> restResponse = (RestResponse<?>) fileUpload.singleFileUpload(spot.getImage());
            spot.setImageUrl(restResponse.getResult().toString());
        }
        spotService.edit(spot);
        return null;
    }

}
