//package com.example.Spotify.config;
//
//import com.example.Spotify.service.JWTService;
//import com.example.Spotify.service.UserDetailsService;
////import jakarta.annotation.Nullable;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.web.filter.OncePerRequestFilter;
//import org.springframework.lang.Nullable;
//import java.io.IOException;
//
//    /// here i used Nullable from org.springframework.lang
//    //  not from jakarta.annotation.Nullable
//
//
//
//@Component
//@RequiredArgsConstructor
////@EnableWebSecurity
//public class JwtAuthenticationFilter extends OncePerRequestFilter {
//
//    private final JWTService jwtService;
//    private final UserDetailsService userDetailsService;
//
//    @Override
//    protected void doFilterInternal(
//            @Nullable HttpServletRequest request,
//            @Nullable HttpServletResponse response,
//            @Nullable FilterChain filterChain
//    ) throws ServletException, IOException {
//
//        //this is the header that contains token
//        final String authorizationHeader = request.getHeader("Authorization");
//        final String jwt;
//        final String userEmail;
//
//        //checking that token exist
//
//        if (StringUtils.isEmpty(authorizationHeader) || !StringUtils.startsWith(authorizationHeader, "Bearer ")) {
//            filterChain.doFilter(request, response);//pass next filter Remember its chain of responsibility pattern
//            return;
//        }
//
//        jwt = authorizationHeader.substring(7); //extracting token from after "Bearer "
//        //jwtService is used to manipulate token extract userEmail from token
//        userEmail = jwtService.extractUserName(jwt);  //next step to extract user name to be able to chech for its details in UserDetailsService
//
//        if (StringUtils.isNotEmpty(userEmail) && SecurityContextHolder.getContext().getAuthentication() == null) {
//            UserDetails userDetails = userDetailsService.userDetailsService().loadUserByUsername(userEmail);
//
//            if (jwtService.isTokenValid(jwt, userDetails)) {
//                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
//                        userDetails,
//                        null,
//                        userDetails.getAuthorities()
//                );
//                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(authToken);
//            }
//        }
//        filterChain.doFilter(request, response);// as this is filter chain so always pass to next filter
//    }
//}
//
