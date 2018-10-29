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
		registrationBean.setFilter( new JwtFilter() );
		registrationBean.addUrlPatterns( "/api/*" );

		return registrationBean;
	}
        
        @Autowired
        private UserRepository userRepository;
        
        @Autowired
        private UserCredentialRepository userCredentialRepository;
        
        @Autowired
        private TokenRepository tokenRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecureApiApplication.class, args);
	}
        
        @Override
        public void run(String... args) throws Exception {
            userRepository.deleteAll();
            userCredentialRepository.deleteAll();
            tokenRepository.deleteAll();
            
            userRepository.save(new User("email1@.com", "Fabian", "Ardila", "email1@.com", "123", "cll 1# 1- 1"));
            userRepository.save(new User("emai2@.com", "Felipe", "Pardo", "email2@.com", "456", "cll 2# 2- 2"));
            userRepository.save(new User("emai3@.com", "Camila", "Gomez", "email3@.com", "789", "cll 3# 3- 3"));
            
            userCredentialRepository.save(new UserCredential("userName1", "hash1", "hashFunction1"));
            userCredentialRepository.save(new UserCredential("userName2", "hash2", "hashFunction2"));
            
            tokenRepository.save(new Token("userName1", "token1", new Date()));
            tokenRepository.save(new Token("userName2", "token2", new Date()));
            
            System.out.println("Users found with findAll():");
            System.out.println("-------------------------------");
            
            for (User usr : userRepository.findAll()) {
                System.out.println(usr);
            }
            System.out.println();
            
            System.out.println("-------------------------------");
            System.out.println();
            
            System.out.println("Users Credentials found with findAll():");
            System.out.println("-------------------------------");
            
            for (UserCredential usrCre : userCredentialRepository.findAll()) {
                System.out.println(usrCre);
            }
            
            System.out.println();
            
            System.out.println("-------------------------------");
            System.out.println();
            
            System.out.println("Token found with findAll():");
            System.out.println("-------------------------------");
            
            for (Token tok : tokenRepository.findAll()) {
                System.out.println(tok);
            }
            
        }
        
}
