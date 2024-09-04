//package com.example.Spotify.service.impl;
//
//import com.example.Spotify.model.User;
//import com.example.Spotify.repository.UserRepository;
//import com.example.Spotify.service.CustomUserDetailsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
////@Service
////public class UserDetailsServiceImpl implements CustomUserDetailsService {
////
////    @Autowired
////    private UserRepository userRepository;
////
////    @Override
////    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
////        User user = userRepository.findByEmail(username)
////                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));
////        return user;
////    }
////
////    @Override
////    public UserDetailsService userDetailsService() {
////        return null;
////    }
////}
//
//
//
//@Service
//public class UserDetailsServiceImpl implements CustomUserDetailsService {
//
//    private final UserRepository userRepository;
//
//    @Autowired
//    public UserDetailsServiceImpl(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByEmail(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));
//        return user;
//    }
//
//    @Override
//    public UserDetailsService userDetailsService() {
//        return null;
//    }
//}
