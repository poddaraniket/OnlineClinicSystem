package com.onlineclinicsystem;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity 
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new CustomerUserDetailsService();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider=new DaoAuthenticationProvider();
		/* authProvider.setUserDetailsService(userDetailsService()); */
		authProvider.setUserDetailsService(this.userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		
		return authProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		    .antMatchers("/oauth2/**").permitAll()
			.antMatchers("/patient/**").hasRole("USER")
			.anyRequest().permitAll()
			.and()
			.formLogin()
				.loginPage("/patientLogin")
				.loginProcessingUrl("/doLogin")	
				.usernameParameter("email")
				.defaultSuccessUrl("/patient/dashboard",true)
     		.and()
     		.oauth2Login()
     			.loginPage("/patientLogin")
     			.userInfoEndpoint().userService(oAuth2userService) //userinfo endpoint
     			.and()
     			.successHandler(oAuth2LoginSuccessHandler)
     		.and()	
     		.logout().permitAll()
     		.logoutUrl("/logout")
     		.logoutSuccessUrl("/")
//     		.logout().invalidateHttpSession(true)
//     			.clearAuthentication(true)
//     			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//     			.logoutSuccessUrl("/logout").permitAll()
     		.and().csrf().disable();
	
	}
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Autowired
	private CustomOAuth2UserService oAuth2userService;
	
	@Autowired
	private OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;
	
}
