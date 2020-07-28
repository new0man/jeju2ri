package kr.huple.jeju2ri.api.controller;

import kr.huple.jeju2ri.api.model.response.PopularKeywordResponse;
import kr.huple.jeju2ri.api.service.PopularKeywordService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/popular-keywords")
public class PopularKeywordController {

    private final PopularKeywordService popularKeywordService;

    public PopularKeywordController(PopularKeywordService popularKeywordService) {
        this.popularKeywordService = popularKeywordService;
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getList() {
        List<PopularKeywordResponse> popularKeywords = popularKeywordService.findAll();
        return popularKeywords;
    }

}
