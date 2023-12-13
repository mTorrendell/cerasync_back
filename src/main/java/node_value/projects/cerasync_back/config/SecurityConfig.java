package node_value.projects.cerasync_back.config;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration @EnableWebSecurity
public class SecurityConfig {
    
    @Autowired private JwtAuthFilter          jwtAuthFilter;
    @Autowired private AuthenticationProvider authProvider;

    @Bean SecurityFilterChain SecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
            aRs -> aRs.requestMatchers("/api/auth/**", "/api/events/public/**", "/api/email/**").permitAll()
                      .anyRequest().authenticated()
        ).sessionManagement(
            sM -> sM.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        ).httpBasic(
            wD -> {}
        ).csrf(
            AbstractHttpConfigurer::disable
        ).cors( 
            wD -> {}
        ).authenticationProvider(
            authProvider
        ).addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
