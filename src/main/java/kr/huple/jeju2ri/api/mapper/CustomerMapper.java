package kr.huple.jeju2ri.api.mapper;

import kr.huple.jeju2ri.api.model.CustomerDto;
import kr.huple.jeju2ri.api.model.response.CustomerResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CustomerMapper {

    public void add(CustomerDto param);

    public Integer findByEmail(String email);

    public Integer findByNickname(String nickname);

    public List<CustomerResponse> findAll();

    public CustomerResponse findByCustomerId(String customerId);

    public List<CustomerResponse> findBySnsId(CustomerDto param);

    public void edit(CustomerDto customer);

}
