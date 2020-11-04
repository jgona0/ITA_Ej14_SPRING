package com.dicegame.demo.security;

import static com.dicegame.demo.security.SecJWTSpecs.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


// Esta clase nos sirve para crear los filtros autenticación y autenticación-satisfactoria. De esta manera decimos que va a pasar cuando 
// el usuario intenta autenticarse y cuando lo logra
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

    private AuthenticationManager authenticationManager;
	
    //CONSUTRUCTOR
    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;

        //setFilterProcessesUrl(SecJWTSpecs.SIGN_UP_URL);   // SI QUEREMOS OTRA URL QUE NO ES LA DE POR DEFECTO PARA AUTENTICAR
    }

	  

    //Implementamos el metodo abstracto de la interfaz que extendemos (gestión de la autenticación)
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {

        // Asumimos que el body tendrá el siguiente JSON  {"username":"ask", "password":"123"}
        // Realizamos un mapeo a nuestra clase User para tener ahi los datos
       	try {
    		
    		User user = new ObjectMapper().readValue(req.getInputStream(), User.class);
    	    		
            // Finalmente autenticamos
            // Spring comparará el user/password recibidos contra el que definimos en la clase SecurityConfig
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getName(),
                            user.getPassword(),
                            new ArrayList<>())
            );
    		
            
    	} catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    
    
    //Sobreescribimos el metodo que dice que se hace cuando la autenticación es exitosa
    @Override
    protected void successfulAuthentication (HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth) throws IOException, ServletException {

    	//Colocamos en una lista de Strings las authorities del usuario. Es decir el rol o roles que este tiene
       List<String> grantedAuthorities = auth.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.toList());
           	
    	// Si la autenticacion fue exitosa, creamos el token y lo agregamos el token a la respuesta    	
    	String token = Jwts.builder()
                .setSubject(
                		//añadimos el nombre
                		auth.getName())

                		//fecha de expiración 
                		.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))

                		//Añadimos la lista de roles
                		.claim("authorities", grantedAuthorities)
                
                		// Hash con el que firmaremos la clave
                		.signWith(SignatureAlgorithm.HS512, SECRET)
                		
                	
                		.compact();

    	
            	//agregamos al encabezado el token
            	res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
            	
            	
            	
    }
}
