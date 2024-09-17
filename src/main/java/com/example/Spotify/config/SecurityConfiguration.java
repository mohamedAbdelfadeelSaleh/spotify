package com.example.Spotify.config;

import com.example.Spotify.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final CustomUserDetailsService userDetailsService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                    .requestMatchers(HttpMethod.POST, "/api/v1/albums").hasRole("ARTIST")
                    .requestMatchers(HttpMethod.GET, "/api/v1/albums").permitAll()
                    .requestMatchers(HttpMethod.GET, "/api/v1/albums/**").authenticated()
                    .requestMatchers(HttpMethod.DELETE, "/api/v1/albums").hasRole("ARTIST")
                    .requestMatchers(HttpMethod.PUT, "/api/v1/albums").hasRole("ARTIST")
                    .requestMatchers(HttpMethod.PUT, "/api/v1/albums/addSong").hasRole("ARTIST")
                    .requestMatchers(HttpMethod.PUT, "/api/v1/albums/removeSong").hasAnyRole("Admin", "ARTIST")
                    .requestMatchers(HttpMethod.GET, "/api/v1/artists").permitAll()
                    .requestMatchers(HttpMethod.GET, "/api/v1/artists/**").authenticated()
                    .requestMatchers("api/v1/auth/**").permitAll()
                    .requestMatchers("/api/v1/comments/**").authenticated()
                    .requestMatchers(HttpMethod.POST, "/api/v1/playlists").hasRole("ARTIST")
                    .requestMatchers(HttpMethod.GET, "/api/v1/playlists").permitAll()
                    .requestMatchers(HttpMethod.GET, "/api/v1/playlists/access").permitAll()
                    .requestMatchers( HttpMethod.DELETE, "/api/v1/playlists").hasAnyRole("ADMIN", "ARTIST")
                    .requestMatchers( "/api/v1/playlists/cahngeVisibility").hasRole("ARTIST")
                    .requestMatchers( "/api/v1/playlists/addSong").hasRole("ARTIST")
                    .requestMatchers( "/api/v1/playlists/removeSong").hasAnyRole("ADMIN", "ARTIST")
                    .requestMatchers("/api/v1/songs/stream").permitAll()
                    .requestMatchers(
                            "/api/v1/songs/search",
                            "/api/v1/songs/like/**",
                            "/api/v1/songs/dislike/**"
                    ).authenticated()
                    .requestMatchers(HttpMethod.POST, "/api/v1/songs").hasRole("ARTIST")
                    .requestMatchers(HttpMethod.DELETE, "/api/v1/songs").hasAnyRole("ADMIN", "ARTIST")
            )
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
