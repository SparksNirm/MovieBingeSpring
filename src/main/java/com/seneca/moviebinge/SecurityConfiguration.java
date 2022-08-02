package com.seneca.moviebinge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import com.seneca.moviebinge.service.UserService;

@SuppressWarnings("deprecation")
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	

	@Autowired
	private UserService userService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.userDetailsService(userService);
		
		//auth.inMemoryAuthentication()
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 http.csrf().disable().
		 authorizeRequests()
		 .antMatchers("/user/**").permitAll()
		 .antMatchers("/auth").permitAll() 
		 .antMatchers("/movies/**").permitAll() 
		 .anyRequest().authenticated();
		 //		 .and().
//		 httpBasic().authenticationEntryPoint(entryPoint);
	}
	
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManager();
	}
}
