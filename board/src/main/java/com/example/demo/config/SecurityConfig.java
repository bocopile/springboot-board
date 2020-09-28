package com.example.demo.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.example.demo.user.UserService;

import lombok.extern.slf4j.Slf4j;

@EnableWebSecurity
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserService userService;
	
	@Override
	public void configure(WebSecurity web) throws Exception{
		 web.ignoring().antMatchers("/css/**", "/script/**", "/image/**", "/fonts/**","/lib/**", "/h2-console/**"); 
	}

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
    	http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/board/**").hasRole("USER")
                .antMatchers("/resource/**").permitAll()
                .and();

        http.formLogin()
                .loginPage("/login")
                .successHandler(successHandler())
                .failureHandler(failureHandler())
                .and()
                .logout()
                .logoutSuccessUrl("/login");

    }

    
    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
    	auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
    	
    	PasswordEncoder encoder = new BCryptPasswordEncoder();
    	
		return encoder;
    	
    }
    

	@Bean 
	public AuthenticationSuccessHandler successHandler() { 
		 return new LoginSuccessHandler("/board/list"); 
	}
	
    @Bean
    public AuthenticationFailureHandler failureHandler() {
        return new AuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

                log.info("loginFailer");

                request.setAttribute("loginid", request.getParameter("username"));
                response.sendRedirect("/login_error?id=" + request.getParameter("username"));

            }
        };
    }
	 

}
