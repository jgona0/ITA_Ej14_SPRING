package com.dicegame.demo.security;

import static com.dicegame.demo.security.SecJWTSpecs.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.*;//SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.ArrayList;
import java.util.List;



//En esta clase definimos la seguridad del proyecto y los usuarios en memoria que vamosa  crear 

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)  //Para poder usar la anotación @PreAuthorized en los controllers
public class SecurityConfig extends WebSecurityConfigurerAdapter {
 	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		/*
		 * 1. Se desactiva el uso de cookies
		 * 2. Se activa la configuración CORS con los valores por defecto
		 * 3. Se desactiva el filtro CSRF
		 * 4. Se indica que el login no requiere autenticación
		 * 5. Se indica que el resto de URLs esten securizadas
		 */
		httpSecurity
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.cors().and()
			.csrf().disable()
			.authorizeRequests().antMatchers(HttpMethod.POST, SIGN_UP_URL).permitAll()
			.anyRequest().authenticated().and()
				.addFilter(new JWTAuthenticationFilter(authenticationManager()))
				.addFilter(new JWTAuthorizationFilter(authenticationManager()));
	}
   
   
   // Dentro de este método creamos los usuarios en memoria y les asignamos roles
   @Autowired
   public void configureGlobal(AuthenticationManagerBuilder auth) 
     throws Exception {
	   
	   List<GrantedAuthority> user_authorities = new ArrayList<GrantedAuthority>();
	   user_authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
	   
	   
	   List<GrantedAuthority> admin_authorities = new ArrayList<GrantedAuthority>();
	   admin_authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
	   admin_authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
	   
		auth.inMemoryAuthentication()
	      .withUser("admin").password(passwordEncoder().encode("pass")).authorities(admin_authorities)
	      .and()
	      .withUser("user").password(passwordEncoder().encode("pass")).authorities(user_authorities);
       
   }
   
   
   
   
   
   
   
   
   
   @Bean
   public PasswordEncoder passwordEncoder() {
       return new BCryptPasswordEncoder();
   }

   @Bean
   public CorsConfigurationSource corsConfigurationSource() {
       final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
       source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());

       return source;
   }
}
