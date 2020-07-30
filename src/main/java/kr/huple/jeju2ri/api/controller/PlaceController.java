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
    public Object findAll() throws Exception {
        List<PlaceDescResponse> placeDesces = placeService.findAll();
        for(int i = 0; i < placeDesces.size(); i++) {
            String placeId = placeDesces.get(i).getPlaceId();
            List<PlaceCategoryResponse> placeCategories = placeService.getPlaceCategory(placeId);
            placeDesces.get(i).setCategories(placeCategories);

        }

        return placeDesces;
    }

    public PlaceFacMainResponse getPlaceFacInfo(String placeId) throws Exception {

        PlaceFacMainResponse placeFacMain = placeService.findPlaceFacByPlaceId(placeId);
        if(placeFacMain != null) {
            // 시절 정보
            List<PlaceFacInfoResponse> placeFacInfos = placeService.findPlaceFacInfoByPlaceId(placeId);
            if (!placeFacInfos.isEmpty()) {
                placeFacMain.setPlaceFacInfos(placeFacInfos);
            }
            // 무장애 시설 정보
            List<PlaceFacInfoResponse> placeBarrierFreeFacInfos = placeService.findPlaceBarrierFreeFacInfoByPlaceId(placeId);
            if (!placeBarrierFreeFacInfos.isEmpty()) {
                placeFacMain.setPlaceBarrierFreeFacInfos(placeBarrierFreeFacInfos);
            }
            // 무장애 시설 이미지
            List<ImageResponse> placeFacImages = imageService.findByImageId(placeFacMain.getImageId());
            if (!placeFacImages.isEmpty()) {
                placeFacMain.setPlaceFacImages(placeFacImages);
            }

        }
        return placeFacMain;
    }

    /**
     * 관광지 상제
     * @param placeId
     * @return PlaceDescResponse
     */
    @GetMapping(value = "/{placeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object findByPlaceId(@PathVariable("placeId") String placeId) throws Exception {

        PlaceMainResponse place = new PlaceMainResponse();

        // 관광지 상세
        PlaceDescResponse placeDesc = placeService.findByPlaceId(placeId);
        List<PlaceCategoryResponse> placeCategories = placeService.getPlaceCategory(placeDesc.getPlaceId());
        // 시설 정보 Main Start
        PlaceFacMainResponse placeFacMain = getPlaceFacInfo(placeId);
        placeDesc.setPlaceFac(placeFacMain);
        // 시설 정보 Main End
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
