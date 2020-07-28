package kr.huple.jeju2ri.api.controller;

import kr.huple.jeju2ri.api.model.CodeDto;
import kr.huple.jeju2ri.api.service.CodeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/codes")
public class CodeController {

    private final CodeService codeService;

    public CodeController(CodeService codeService) {
        this.codeService = codeService;
    }

    @GetMapping(value = "/{code}")
    public Object getLargeCategory(@PathVariable("code") String code) throws Exception {
        List<CodeDto> categoryList = codeService.findByUpperCd(code);
        return categoryList;
    }

}
