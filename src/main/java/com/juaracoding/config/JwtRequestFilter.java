package com.juaracoding.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.juaracoding.service.JwtPenumpangDetailService;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	JwtPenumpangDetailService jwtPenumpangDetailService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String requestTokenHeader = request.getHeader("Authorization");
//		Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYW1lIjoiSnVhcmEgQ29kaW5nIn0.nFgKqog3l5cnZ4gB7V8FG9IriHrt67hq-JhClqnfkrU
		String jwtToken = null;
		String username = null;
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);
			try {
				username = jwtTokenUtil.getUsernameFromToken(jwtToken);
			} catch(IllegalArgumentException e) {
				System.out.println("Tidak bisa mendapatkan JWT Token");
			} catch(ExpiredJwtException e) {
				System.out.println("Jwt token anda sudah kadaluwarsa, silahkan generate ulang");
			}
		} else {
			logger.warn("JWT anda tidak dimulai dari kata bearer");
		}
		
//		ketika kita dapet tokenn, kita validasi codenya di bawah ini
		if(username != null &&SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = jwtPenumpangDetailService.loadUserByUsername(username);
			
//			jika token valid, akan melakukan config
			if(jwtTokenUtil.validateToken(jwtToken, userDetails)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
						new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				
				usernamePasswordAuthenticationToken.setDetails(
						new WebAuthenticationDetailsSource().buildDetails(request));
				
				
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		filterChain.doFilter(request, response);
		
	}

}
