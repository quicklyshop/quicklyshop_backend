package com.eci.cosw.project.quicklyshop.security;

import com.eci.cosw.project.quicklyshop.security.config.JwtFilter;
import com.eci.cosw.project.quicklyshop.security.model.User;
import com.eci.cosw.project.quicklyshop.security.service.persistence.UserRepository;
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
		registrationBean.setFilter( new JwtFilter() );
		registrationBean.addUrlPatterns( "/api/*" );

		return registrationBean;
	}
        
        @Autowired
        private UserRepository userRepository;


	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecureApiApplication.class, args);
	}
        
        @Override
        public void run(String... args) throws Exception {
            userRepository.deleteAll();
            
            userRepository.save(new User("email1@.com", "Fabian", "Ardila", "email1@.com", "123", "cll 1# 1- 1"));
            userRepository.save(new User("emai2@.com", "Felipe", "Pardo", "email2@.com", "456", "cll 2# 2- 2"));
            userRepository.save(new User("emai3@.com", "Camila", "Gomez", "email3@.com", "789", "cll 3# 3- 3"));
            
            System.out.println("Users found with findAll():");
            System.out.println("-------------------------------");
            
            for (User usr : userRepository.findAll()) {
                System.out.println(usr);
            }
            System.out.println();
        }
        
}
