//package com.example.Spotify.config;
//
//import com.example.Spotify.config.JwtAuthenticationFilter;
//import com.example.Spotify.enums.Role;
//import com.example.Spotify.service.CustomOAuth2UserService;
//import com.example.Spotify.service.CustomUserDetailsService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
//import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
//import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
//import org.springframework.security.oauth2.core.oidc.user.OidcUser;
//import org.springframework.security.oauth2.core.user.OAuth2User;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class SecurityConfiguration {
//
//    private final JwtAuthenticationFilter jwtAuthenticationFilter;
//    private final CustomUserDetailsService userDetailsService;
//    private final CustomOAuth2UserService customOAuth2UserService;
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/api/v1/auth/**").permitAll()
//                        .requestMatchers("/api/v1/music/listen/**").permitAll()
////                        .requestMatchers(HttpMethod.DELETE, "/api/v1/songs/**", "/api/v1/albums/**")
////                        .hasAnyAuthority(Role.ARTIST.name(), Role.ADMIN.name())
////                        .requestMatchers(HttpMethod.POST, "/api/v1/songs/**", "/api/v1/albums/**")
////                        .hasAuthority(Role.ARTIST.name())
////                        .requestMatchers(HttpMethod.POST, "/api/v1/playlists/**").authenticated()
//                        .anyRequest().authenticated()
////                                .anyRequest().permitAll()
//                )
//                .oauth2Login(oauth2 -> oauth2
//                        .loginPage("/api/v1/auth/login")
//                        .userInfoEndpoint(userInfo -> userInfo.userService(customOAuth2UserService))
//                        .defaultSuccessUrl("/api/v1/auth/success", true))
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .authenticationProvider(authenticationProvider())
//                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//    }
//
//    @Bean
//    public DaoAuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(userDetailsService);
//        authProvider.setPasswordEncoder(passwordEncoder());
//        return authProvider;
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
//        return config.getAuthenticationManager();
//    }
//}
//
//
//
//
//
//
////
////@Configuration
////@EnableWebSecurity
////@RequiredArgsConstructor
////public class SecurityConfiguration {
////
////    private final JwtAuthenticationFilter jwtAuthenticationFilter;
////    private final CustomUserDetailsService userDetailsService;
////    private final OAuth2UserService<OidcUserRequest, OidcUser> oidcUserService;  // Add OIDC User Service
////    private final OAuth2UserService<OAuth2UserRequest, OAuth2User> oauth2UserService; // Add OAuth2 User Service
////
////
////    @Bean
////    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////        http.csrf(AbstractHttpConfigurer::disable)
////                .authorizeHttpRequests(auth -> auth
////                        // Permit all to access login and signup
////                        .requestMatchers("/api/v1/auth/**").permitAll()
////
////                        // Allow only authenticated users to listen to music
////                        .requestMatchers("/api/v1/music/listen/**").authenticated()
////                        // Allow only ARTIST and ADMIN roles to delete song or album
////                        .requestMatchers(HttpMethod.DELETE, "/api/v1/songs/**", "/api/v1/albums/**")
////                        .hasAnyAuthority(Role.ARTIST.name(), Role.ADMIN.name())
////                        // Allow only ARTIST role to add song or album
////                        .requestMatchers(HttpMethod.POST, "/api/v1/songs/**", "/api/v1/albums/**")
////                        .hasAuthority(Role.ARTIST.name())
////                        // Allow only authenticated users to create playlists
////                        .requestMatchers(HttpMethod.POST, "/api/v1/playlists/**").authenticated()
////                        // Any other request should be allowed only for authenticated users
////                        .anyRequest().authenticated()
////                )
////                .oauth2Login(oauth2 -> oauth2
////                        .loginPage("/api/v1/auth/login")
////                        .userInfoEndpoint(userInfo -> userInfo
////                                .oidcUserService(oidcUserService)
////                                .userService(oauth2UserService))
////                        .defaultSuccessUrl("/api/v1/auth/success", true))
////                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
////                .authenticationProvider(authenticationProvider())
////                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
////
////        return http.build();
////    }
////
////    @Bean
////    public DaoAuthenticationProvider authenticationProvider() {
////        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
////        authProvider.setUserDetailsService(userDetailsService);
////        authProvider.setPasswordEncoder(passwordEncoder());
////        return authProvider;
////    }
////
////    @Bean
////    public PasswordEncoder passwordEncoder() {
////        return new BCryptPasswordEncoder();
////    }
////
////    @Bean
////    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
////        return config.getAuthenticationManager();
////    }
////}
