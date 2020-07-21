package almeida.ferreira.junio.bluetasks.infrastructure.web.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import almeida.ferreira.junio.bluetasks.domain.user.AppUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	
	private AuthenticationManager authManager;
	
	public JWTAuthenticationFilter(AuthenticationManager authManager) {
		this.authManager = authManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		ObjectMapper mapper = new ObjectMapper();
		AppUser user;
		
		try {
			user = mapper.readValue(request.getInputStream(), AppUser.class);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
		
		return authManager.authenticate(upat);
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		UserDetailsImpl user = (UserDetailsImpl) authResult.getPrincipal();
		
		String jwtToken = Jwts.builder()
			.setSubject(user.getUsername())
			.setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
			.claim("displayName", user.getUser().getDisplayName())
			.signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET_KEY)
			.compact();
		
		response.addHeader(SecurityConstants.AUTHORIZATION_HEADER_TITLE, SecurityConstants.TOKEN_PREFIX + jwtToken);			
	}
	
}
