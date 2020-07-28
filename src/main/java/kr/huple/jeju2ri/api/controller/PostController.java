package kr.huple.jeju2ri.api.controller;

import kr.huple.jeju2ri.api.model.response.*;
import kr.huple.jeju2ri.api.service.*;
import kr.huple.jeju2ri.util.Word;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/posts")
public class PostController {

    private final PostService postService;
    private final PlaceService placeService;
    private final IntervieweeService intervieweeService;
    private final HoneyTipService honeyTipService;
    private final ImageService imageService;

    public PostController(PostService postService
                          , PlaceService placeService
                          , IntervieweeService intervieweeService
                          , HoneyTipService honeyTipService
                          , ImageService imageService) {
        this.postService = postService;
        this.placeService = placeService;
        this.intervieweeService = intervieweeService;
        this.honeyTipService = honeyTipService;
        this.imageService = imageService;
    }

    /**
     * Post 상세
     * @param postId
     * @return
     */
    @GetMapping(value = "/{postId}")
    public Object findByPostId(@PathVariable("postId") String postId) {

        PostMainResponse postMain = new PostMainResponse();

        // Post 상세
        PostDescResponse postDesc = postService.findByPostId(postId);
        if(postDesc == null) {
            return Word.NO_POST_MSG; // 포스트가 없습니다.
        }
        // Place 상세
        String placeId = postDesc.getPlaceId();
        PlaceDescResponse placeDesc = placeService.findByPlaceId(placeId);
        List<PlaceCategoryResponse> placeCategories = placeService.getPlaceCategory(placeId);
        placeDesc.setCategories(placeCategories);
        // Interviewee 상세
        String intervieweeId = postDesc.getIntervieweeId();
        IntervieweeDescResponse intervieweeDesc = intervieweeService.findByIntervieweeId(intervieweeId);
        // Post 목록
        List<PostResponse> posts = postService.findByPlaceId(placeId);
        // 꿀팁
        List<HoneyTipResponse> honeyTips = honeyTipService.findByPlaceId(placeId);
        for(int i = 0; i < honeyTips.size(); i++) {
            List<ImageResponse> images = imageService.findByImageId(honeyTips.get(i).getImageId());
            honeyTips.get(i).setImages(images);
        }

        postMain.setPostDesc(postDesc);
        postMain.setPlaceDesc(placeDesc);
        postMain.setIntervieweeDesc(intervieweeDesc);
        postMain.setPosts(posts);
        postMain.setHoneyTips(honeyTips);

        return postMain;

    }

}
