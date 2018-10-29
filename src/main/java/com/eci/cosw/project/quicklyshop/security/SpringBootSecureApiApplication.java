package com.eci.cosw.project.quicklyshop.security;

import com.eci.cosw.project.quicklyshop.security.config.JwtFilter;
import com.eci.cosw.project.quicklyshop.security.model.Token;
import com.eci.cosw.project.quicklyshop.security.model.User;
import com.eci.cosw.project.quicklyshop.security.model.UserCredential;
import com.eci.cosw.project.quicklyshop.security.service.usercredential.persistence.TokenRepository;
import com.eci.cosw.project.quicklyshop.security.service.usercredential.persistence.UserCredentialRepository;
import com.eci.cosw.project.quicklyshop.security.service.user.persistence.UserRepository;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootSecureApiApplication implements CommandLineRunner {


    @Bean
    public FilterRegistrationBean jwtFilter() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new JwtFilter());
        registrationBean.addUrlPatterns("/api/*");

        return registrationBean;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootSecureApiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Procurar no dejar comandos aqui de prueba
    }

}
