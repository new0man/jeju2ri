package kr.huple.jeju2ri.api.controller;

import kr.huple.jeju2ri.api.model.HoneyTip;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class HoneyTipController {

    /**
     * 꿀팁 목록 조회
     * @param placeId
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/places/{placeId}/honey-tips")
    public Object findByPlaceId(@PathVariable("placeId") String placeId) throws Exception {
        return null;
    }

    /**
     * 꿀팁 등록
     * @param placeId
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/places/{placeId}/honey-tips")
    public Object add(@PathVariable("placeId") String placeId, @RequestBody HoneyTip param) throws Exception {
        return null;
    }

    /**
     * 꿀팁 수정
     * @param tipId
     * @return
     * @throws Exception
     */
    @PutMapping(value = "/honey-tips/{tipId}")
    public Object edit(@PathVariable("tipId") Integer tipId, @RequestBody HoneyTip param) throws Exception {
        return null;
    }

    /**
     * 꿀팁 삭제
     * @param tipId
     * @return
     * @throws Exception
     */
    @DeleteMapping(value = "/honey-tips/{tipId}")
    public Object delete(@PathVariable("tipId") Integer tipId) throws Exception {
        return null;
    }

}
