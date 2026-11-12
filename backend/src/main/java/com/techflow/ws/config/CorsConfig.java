package com.techflow.ws.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

// CORS restricted to explicit origins (CORS_ALLOWED_ORIGINS, comma separated).
// The bundled web front is same-origin and does not depend on it.
@Configuration
public class CorsConfig {

	private static final String DEFAULT_ORIGINS = "http://localhost:8089";

	@Bean
	public CorsConfigurationSource corsConfigurationSource(Environment env) {
		String origins = env.getProperty("CORS_ALLOWED_ORIGINS", DEFAULT_ORIGINS);
		List<String> allowed = Arrays.stream(origins.split(",")).map(String::trim).filter(o -> !o.isEmpty()).toList();

		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(allowed);
		configuration.setAllowedMethods(List.of("GET", "POST", "PATCH", "PUT", "DELETE", "OPTIONS"));
		configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
		configuration.setAllowCredentials(false);
		configuration.setMaxAge(3600L);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/api/**", configuration);
		return source;
	}
}
