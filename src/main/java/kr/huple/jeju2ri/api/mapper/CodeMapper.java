package kr.huple.jeju2ri.api.mapper;

import kr.huple.jeju2ri.api.model.Code;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CodeMapper {

    public List<Code> findByUpperCd(String upperCd);

}
