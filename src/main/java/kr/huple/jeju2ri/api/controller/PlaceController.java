package kr.huple.jeju2ri.api.controller;

import kr.huple.jeju2ri.api.model.response.*;
import kr.huple.jeju2ri.api.service.HoneyTipService;
import kr.huple.jeju2ri.api.service.ImageService;
import kr.huple.jeju2ri.api.service.PlaceService;
import kr.huple.jeju2ri.api.service.PostService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/places")
public class PlaceController {

    private final PlaceService placeService;
    private final PostService postService;
    private final HoneyTipService honeyTipService;
    private final ImageService imageService;

    public PlaceController(PlaceService placeService, PostService postService, HoneyTipService honeyTipService, ImageService imageService) {
        this.placeService = placeService;
        this.postService = postService;
        this.honeyTipService = honeyTipService;
        this.imageService = imageService;
    }

    /**
     * 관광지 전체 목록
     * @return List<PlaceDescResponse>
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object findAll() {
        List<PlaceDescResponse> placeDesces = placeService.findAll();
        for(int i = 0; i < placeDesces.size(); i++) {
            List<PlaceCategoryResponse> placeCategories = placeService.getPlaceCategory(placeDesces.get(i).getPlaceId());
            placeDesces.get(i).setCategories(placeCategories);
        }

        return placeDesces;
    }

    /**
     * 관광지 상제
     * @param placeId
     * @return PlaceDescResponse
     */
    @GetMapping(value = "/{placeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object findByPlaceId(@PathVariable("placeId") String placeId) {

        PlaceMainResponse place = new PlaceMainResponse();

        // 관광지 상세
        PlaceDescResponse placeDesc = placeService.findByPlaceId(placeId);
        List<PlaceCategoryResponse> placeCategories = placeService.getPlaceCategory(placeDesc.getPlaceId());
        placeDesc.setCategories(placeCategories);

        List<PostResponse> posts = postService.findByPlaceId(placeId);

        List<HoneyTipResponse> honeyTips = honeyTipService.findByPlaceId(placeId);
        for(int i = 0; i < honeyTips.size(); i++) {
            List<ImageResponse> images = imageService.findByImageId(honeyTips.get(i).getImageId());
            honeyTips.get(i).setImages(images);
        }

        place.setPlaceDesc(placeDesc);
        place.setPosts(posts);
        place.setHoneyTips(honeyTips);

        return place;
    }

}
