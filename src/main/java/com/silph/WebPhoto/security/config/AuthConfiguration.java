/*
package com.silph.WebPhoto.security.config;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class AuthConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private Environment environment;
	
	private DataSource dataSource;
				
	
	@Override
	protected void configure (HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
			.antMatchers(HttpMethod.GET, "/**").permitAll()
			.antMatchers(HttpMethod.POST, "/**").permitAll()
			.antMatchers(HttpMethod.GET, "/static/images/**").permitAll()
			.antMatchers(HttpMethod.GET, "/admin/**").hasAnyAuthority("ADMIN")
			.antMatchers(HttpMethod.POST, "/admin/**").hasAnyAuthority("ADMIN")
			.anyRequest().authenticated()
		.and().formLogin()
			.defaultSuccessUrl("/admin")
		.and().logout()
			.logoutUrl("/logout")
			.logoutSuccessUrl("/");
	}
	
	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(this.buildDatasource())
			.authoritiesByUsernameQuery("SELECT username, role FROM users WHERE username=?")
			.usersByUsernameQuery("SELECT username, password, 1 as enabled FROM users WHERE username=?");
	}
	
	
	@Bean
	DataSource buildDatasource() {
		
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getProperty("spring.datasource.driver-class-name"));
        dataSource.setUrl(environment.getProperty("spring.datasource.url"));
        dataSource.setUsername(environment.getProperty("spring.datasource.username"));
        dataSource.setPassword(environment.getProperty("spring.datasource.password"));
        
        return dataSource;
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
*/
