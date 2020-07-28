package kr.huple.jeju2ri.api.controller;

import kr.huple.jeju2ri.api.model.SearchDto;
import kr.huple.jeju2ri.api.service.SearchService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping(value = "/search-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object searchList(@RequestParam String[] locations, @RequestParam String keyword) throws Exception {
        SearchDto param = new SearchDto();
        param.setLocations(locations);
        param.setKeyword(keyword);

        return searchService.findByKeyword(param);
    }

    @GetMapping(value = "/search-posts", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object searchPost(@RequestParam String placeId) throws Exception {
        return searchService.findByPlaceId(placeId);
    }

}
