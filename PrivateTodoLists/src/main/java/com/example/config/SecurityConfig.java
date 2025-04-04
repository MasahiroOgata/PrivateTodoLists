package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	
	@Autowired
	private AuthenticationSuccessHandler customAuthenticationSuccessHandler;
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests(authorize -> authorize
				.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
				.requestMatchers("/signup").permitAll()
				.anyRequest()
				//.permitAll() // 開発用暫定措置（完成後下記に変更）
				.authenticated()
		);
		
		http.formLogin(login -> login
				.loginProcessingUrl("/login")
				.loginPage("/login")
				.failureUrl("/login?error")
				.usernameParameter("userId")
				.passwordParameter("password")
				.successHandler(customAuthenticationSuccessHandler)
				//.defaultSuccessUrl("/todo/list", true)
				.permitAll()
		).logout(logout -> logout
				.logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .permitAll()
        );

		http.headers(headers -> headers
				.frameOptions(FrameOptionsConfig::disable)
		);
		
		
	    // CSRF 対策を無効に設定 (一時的)
        http.csrf(csrf -> csrf
        		.disable()
        ); 
		
		return http.build();
		
	}
	
	
	/*
	@Bean
    InMemoryUserDetailsManager userDetailsService() {
        PasswordEncoder encoder = passwordEncoder();

        UserDetails user = User.withUsername("user")
                .password(encoder.encode("user"))
                .roles("GENERAL")
                .build();
        UserDetails admin = User.withUsername("admin")
                .password(encoder.encode("admin"))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }
    */

}
