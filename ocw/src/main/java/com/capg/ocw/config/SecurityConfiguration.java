package com.capg.ocw.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.capg.ocw.config.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	CustomUserDetailsService customUserDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
		/*.antMatchers("/oauth/token").permitAll()
		.antMatchers("/login").permitAll()
		.antMatchers("/register").permitAll()
		.antMatchers("/api/**").hasAnyAuthority("ADMIN","CUSTOMER","WASHER")
		*/.anyRequest().authenticated()
			.and().httpBasic();
		/*.failureUrl("/login?error=true")
		.defaultSuccessUrl("/api/home")
		.usernameParameter("userId")
		.passwordParameter("password");.and()
		.exceptionHandling().accessDeniedPage("/access-denied")*/
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	 @Autowired
	    public void configureGlobal(AuthenticationManagerBuilder authentication)
	            throws Exception
	    {
	        authentication.inMemoryAuthentication()/*.withUser(customUserDetailsService.loadUserByUsername(authentication.u))*/
	                .withUser("sri")
	                .password(passwordEncoder().encode("sri123"))
	                .authorities("ROLE_USER");
	    }
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
