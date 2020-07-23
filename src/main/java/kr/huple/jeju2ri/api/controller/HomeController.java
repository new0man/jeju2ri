package kr.huple.jeju2ri.api.controller;

import kr.huple.jeju2ri.api.model.Code;
import kr.huple.jeju2ri.api.model.response.HomeCategoryResponse;
import kr.huple.jeju2ri.api.model.response.HomePostResponse;
import kr.huple.jeju2ri.api.model.response.HomeResponse;
import kr.huple.jeju2ri.api.model.response.HomeTodayResponse;
import kr.huple.jeju2ri.api.service.CodeService;
import kr.huple.jeju2ri.api.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/home")
public class HomeController {

    private final HomeService homeService;
    private final CodeService codeService;

    public HomeController(HomeService homeService, CodeService codeService) {
        this.homeService = homeService;
        this.codeService = codeService;
    }

    /**
     * 홈 API
     * @return
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getList() {

        HomeResponse home = new HomeResponse();

        HomeTodayResponse homeToday = new HomeTodayResponse();

        List<HomePostResponse> posts = homeService.top10Post();
        HomePostResponse barrierFreePost = homeService.barrierFreePost();

        homeToday.setPosts(posts);
        homeToday.setBarrierFreePost(barrierFreePost);

        home.setToday(homeToday);

        HomeCategoryResponse homeCategory = null;

        List<Code> categoryList = codeService.findByUpperCd("A01");

        System.out.println("categoryList : " + categoryList.size());

        List<HomePostResponse> categoryPosts = null;
        List<HomePostResponse> bestPosts = null;
        for(int i = 0; i < categoryList.size(); i++) {

            homeCategory = new HomeCategoryResponse();
            homeCategory.setCategory(categoryList.get(i).getCdNm());
            homeCategory.setHtmlColorCode(categoryList.get(i).getVal2());

            categoryPosts = homeService.postByCategory(categoryList.get(i).getCd());
            bestPosts = homeService.bestPostByCategory(categoryList.get(i).getCd());

            if(bestPosts != null && bestPosts.size() > 0) {
                homeCategory.setBestPosts(bestPosts);
            }

            if(categoryPosts != null && categoryPosts.size() > 0) {
                homeCategory.setPosts(categoryPosts);
            }

            home.getCategories().add(homeCategory);

        }

        return home;

    }

}
