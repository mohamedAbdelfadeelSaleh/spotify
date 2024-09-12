package com.example.Spotify.repository;

import com.example.Spotify.enums.Role;
import com.example.Spotify.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//    User findByUsername(String username);
    Optional<User> findByEmail(String email);
    User findByRole(Role role);

}
