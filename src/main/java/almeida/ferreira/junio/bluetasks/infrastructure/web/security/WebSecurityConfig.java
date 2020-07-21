package almeida.ferreira.junio.bluetasks.infrastructure.web.security;

import static almeida.ferreira.junio.bluetasks.utils.LoggerUtils.getLoggerFromClass;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer{
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().cors()
			.and()
				.headers().frameOptions().disable()
			.and()
				.httpBasic()
			.and()
				.authorizeRequests()
					.antMatchers("/h2-console/**").permitAll()
					.anyRequest().authenticated()
			.and()
				.addFilter(new JWTAuthenticationFilter(authenticationManager()))
				.addFilter(new JWTAuthorizationFilter(authenticationManager()))
			.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		getLoggerFromClass(WebSecurityConfig.class).info("Security setup...OK");
	}
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/login")
			.allowedOrigins("*")
			.allowedMethods("POST")
			.exposedHeaders(SecurityConstants.AUTHORIZATION_HEADER_TITLE);
		
		getLoggerFromClass(WebSecurityConfig.class).info("Cors Registry setup...OK");
	}
	
}
