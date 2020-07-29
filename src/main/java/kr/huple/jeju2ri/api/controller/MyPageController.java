package kr.huple.jeju2ri.api.controller;

import kr.huple.jeju2ri.api.model.CodeDto;
import kr.huple.jeju2ri.api.model.MyPageDto;
import kr.huple.jeju2ri.api.model.response.CategoryMyPlanResponse;
import kr.huple.jeju2ri.api.model.response.MyPageMyPlanResponse;
import kr.huple.jeju2ri.api.model.response.MyPageMyPlanScheduleResponse;
import kr.huple.jeju2ri.api.model.response.MyPageResponse;
import kr.huple.jeju2ri.api.service.CodeService;
import kr.huple.jeju2ri.api.service.MyPageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/my-page")
public class MyPageController {

    private final MyPageService myPageService;
    private final CodeService codeService;

    public MyPageController(MyPageService myPageService, CodeService codeService) {
        this.myPageService = myPageService;
        this.codeService = codeService;
    }

    @GetMapping(value = "/{customerId}")
    public Object myPage(@PathVariable("customerId") String customerId) throws  Exception {

        MyPageResponse myPage = new MyPageResponse();

        List<CategoryMyPlanResponse> categoryMyPlans = myPageService.findPlaceByCustomerId(customerId);
        myPage.setCategoryMyPlans(categoryMyPlans);

        List<MyPageMyPlanResponse> myPlans = myPageService.findPlanByCustomerId(customerId);
        myPage.setMyPageMyPlans(myPlans);

        return myPage;

    }

    @GetMapping(value = "/{customerId}/category/{largeCategoryCd}")
    public Object categoryPlanSchedule(@PathVariable("customerId") String customerId, @PathVariable("largeCategoryCd") String largeCategoryCd) throws  Exception {

        MyPageDto param = new MyPageDto();
        param.setCustomerId(customerId);
        param.setLargeCategoryCd(largeCategoryCd);
        List<MyPageMyPlanResponse> myPlans = myPageService.findCategoryPlan(param);
        for (int i = 0; i < myPlans.size(); i++) {
            param.setPlanId(myPlans.get(i).getPlanId());
            List<MyPageMyPlanScheduleResponse> myPlanSchedules = myPageService.findCategorySchedule(param);
            myPlans.get(i).setMyPlanSchedules(myPlanSchedules);
        }

        return myPlans;

    }

}
