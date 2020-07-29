package kr.huple.jeju2ri.api.controller;

import kr.huple.jeju2ri.api.model.FileDto;
import kr.huple.jeju2ri.api.model.HoneyTipDto;
import kr.huple.jeju2ri.api.model.ImageDto;
import kr.huple.jeju2ri.api.model.response.HoneyTipResponse;
import kr.huple.jeju2ri.api.model.response.ImageResponse;
import kr.huple.jeju2ri.api.service.HoneyTipService;
import kr.huple.jeju2ri.api.service.ImageService;
import kr.huple.jeju2ri.configuration.response.RestResponse;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class HoneyTipController {

    private final HoneyTipService honeyTipService;
    private final ImageService imageService;
    private final FileUploadController fileUpload;

    public HoneyTipController(HoneyTipService honeyTipService, ImageService imageService, FileUploadController fileUpload) {
        this.honeyTipService = honeyTipService;
        this.imageService = imageService;
        this.fileUpload = fileUpload;
    }

    /**
     * 꿀팁 목록 조회
     *
     * @param placeId
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/places/{placeId}/honey-tips")
    public Object findByPlaceId(@PathVariable("placeId") String placeId) throws Exception {
        List<HoneyTipResponse> honeyTips = honeyTipService.findByPlaceId(placeId);
        for (int i = 0; i < honeyTips.size(); i++) {
            List<ImageResponse> images = imageService.findByImageId(honeyTips.get(i).getImageId());
            if (images != null) {
                honeyTips.get(i).setImages(images);
            }
        }
        return honeyTips;
    }

    /**
     * 꿀팁 상세 조회
     *
     * @param tipId
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/honey-tips/{tipId}")
    public Object findByTipId(@PathVariable("tipId") Long tipId) throws Exception {
        HoneyTipResponse honeyTip = honeyTipService.findByTipId(tipId);
        List<ImageResponse> images = imageService.findByImageId(honeyTip.getImageId());
        if (images != null) {
            honeyTip.setImages(images);
        }
        return honeyTip;
    }

    /**
     * 꿀팁 등록
     *
     * @param param
     * @return
     * @throws Exception
     */
    @Transactional
    @PostMapping(value = "/honey-tips", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Object add(@RequestBody HoneyTipDto param) throws Exception {
        String imageId = imageService.getImageId();
        param.setImageId(imageId);
        honeyTipService.add(param);
        return null;
    }

    /**
     * 꿀팁 수정
     *
     * @param tipId
     * @return
     * @throws Exception
     */
    @PutMapping(value = "/honey-tips/{tipId}")
    public Object edit(@PathVariable("tipId") Long tipId, @RequestBody HoneyTipDto param) throws Exception {
        param.setTipId(tipId);
        honeyTipService.edit(param);
        return null;
    }

    /**
     * 꿀팁 삭제
     *
     * @param tipId
     * @return
     * @throws Exception
     */
    @DeleteMapping(value = "/honey-tips/{tipId}")
    public Object delete(@PathVariable("tipId") Long tipId) throws Exception {
        honeyTipService.delete(tipId);
        return null;
    }

}
