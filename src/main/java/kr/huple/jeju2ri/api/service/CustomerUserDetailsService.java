package kr.huple.jeju2ri.api.service;

import kr.huple.jeju2ri.api.mapper.CustomerMapper;
import kr.huple.jeju2ri.api.model.CustomerDto;
import kr.huple.jeju2ri.api.model.response.CustomerResponse;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerUserDetailsService implements UserDetailsService {

    private final CustomerMapper customerMapper;

    public CustomerUserDetailsService(CustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("================== loadUserByUsername called !!! ===============");
        String usernamePrefix = "SNSID:";
        if (username.startsWith(usernamePrefix)) {

            String[] userInfo = username.substring(usernamePrefix.length()).split("❤");
            System.out.println("SNS ID =>  " + userInfo[0]);
            System.out.println("join method =>  " + userInfo[1]);

            // SNS 아이디와 타입을 가져온다.
            CustomerDto param = new CustomerDto();
            param.setSnsId(userInfo[0]);
            param.setJoinMethod(userInfo[1]);

            List<CustomerResponse> customerList = customerMapper.findBySnsId(param);
            if (customerList.size() > 0) {
                CustomerResponse customer = customerList.get(0);
                String password = "{noop}" + customer.getJoinMethod();
                return new org.springframework.security.core.userdetails.User(customer.getSnsId(), password, new ArrayList<>());
            } else {
                throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
            }
        } else {
            // 유저 아이디와 암호를 가져온다.
            return new org.springframework.security.core.userdetails.User("username", "password", new ArrayList<>());
        }
    }

}
