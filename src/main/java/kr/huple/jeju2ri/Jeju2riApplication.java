package kr.huple.jeju2ri;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class Jeju2riApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Jeju2riApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Jeju2riApplication.class);
    }

}
