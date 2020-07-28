package kr.huple.jeju2ri.api.mapper;

import kr.huple.jeju2ri.api.model.CodeDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CodeMapper {

    public List<CodeDto> findByUpperCd(String upperCd);

}
