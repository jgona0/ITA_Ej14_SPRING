package com.dicegame.demo.security;

import static com.dicegame.demo.security.SecJWTSpecs.*;


import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Jwts;


// En esta clase definimos los filtros de autorización una vez el user está autenticado  
public class JWTAuthorizationFilter extends BasicAuthenticationFilter{

	
	public JWTAuthorizationFilter(AuthenticationManager authManager) {
        super(authManager);
    }
	
	
	
	
    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
       
        
        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
        
        if (authentication == null) {
        	chain.doFilter(req, res);
            return;
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }
    
    
    
    
    // En este metodo gestionamos la información del token de la request http
    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        
    	String token = request.getHeader(HEADER_STRING);
        
    	if (token != null) {
    		
    		//Recuperamos del token toda la info
    		String user = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, "")) //este metodo es el que valida
                    .getBody()
                    .getSubject();

    		
    		// Recuperamos del token la lista de ROLES
    		List<String> authorities = (List) Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody().get("authorities");

            
            // Para las demás peticiones que no sean /login no requerimos una autenticacion por username/password
            // por este motivo podemos devolver un UsernamePasswordAuthenticationToken sin password pero con el ROL/ES del USER
            if (user != null) {
            	
                return new UsernamePasswordAuthenticationToken(user, null, authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
            }
        }
        return null;
    }
	
}
