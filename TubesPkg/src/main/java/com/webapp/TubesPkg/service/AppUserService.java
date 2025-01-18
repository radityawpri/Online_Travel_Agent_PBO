package com.webapp.TubesPkg.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.webapp.TubesPkg.models.AppUser;
import com.webapp.TubesPkg.repository.AppUserRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j 
public class AppUserService implements UserDetailsService {
    @Autowired
    private AppUserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser appUser = repo.findByEmail(email);
        if (appUser == null) {
            log.warn("User not found with email: {}", email); // Gunakan log.warn()
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        if (appUser.getRole() != null) {
            String roleWithPrefix = "ROLE_" + appUser.getRole().toUpperCase();
            authorities.add(new SimpleGrantedAuthority(roleWithPrefix));
            log.info("User {} logged in with authorities {}", appUser.getEmail(), authorities); // Gunakan log.info()
        }

        return new User(
            appUser.getEmail(),
            appUser.getPassword(),
            authorities
        );
    }
}

