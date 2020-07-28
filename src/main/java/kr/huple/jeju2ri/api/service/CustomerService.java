package kr.huple.jeju2ri.api.service;

import kr.huple.jeju2ri.api.mapper.CustomerMapper;
import kr.huple.jeju2ri.api.model.CustomerDto;
import kr.huple.jeju2ri.api.model.response.CustomerResponse;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerMapper customerMapper;

    public CustomerService(CustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
    }

    public void add(CustomerDto param) {
        customerMapper.add(param);
    }

    public Integer findByEmail(String email) {
        //Validate.isTrue(!customerMapper.findByEmail(email).isPresent(), "true");
        return customerMapper.findByEmail(email);
    }

    public Integer findByNickname(String nickname) {
        //Validate.isTrue(!customerMapper.findByNickname(nickname).isPresent(), "true");
        return customerMapper.findByNickname(nickname);
    }

    public List<CustomerResponse> findAll() {
        return customerMapper.findAll();
    }

    public Object findByCustomerId(String customerId) {
        return customerMapper.findByCustomerId(customerId);
    }

    public List<CustomerResponse> findBySnsId(@Validated CustomerDto param) {
        return customerMapper.findBySnsId(param);
    }

    public void edit(CustomerDto param) {
        customerMapper.edit(param);
    }

}
