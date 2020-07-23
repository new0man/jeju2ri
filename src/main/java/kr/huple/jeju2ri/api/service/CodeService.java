package kr.huple.jeju2ri.api.service;

import kr.huple.jeju2ri.api.mapper.CodeMapper;
import kr.huple.jeju2ri.api.model.Code;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CodeService {

    private final CodeMapper codeMapper;

    public CodeService(CodeMapper codeMapper) {
        this.codeMapper = codeMapper;
    }

    public List<Code> findByUpperCd(String upperCd) {
        return codeMapper.findByUpperCd(upperCd);
    }

}
