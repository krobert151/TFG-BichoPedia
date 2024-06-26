package com.robertorebolledonaharro.bichoapi.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("personDetailsService")
@RequiredArgsConstructor
public class CustomPersonDetailsService implements UserDetailsService {


    private final UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userService.findUserByUsername(username);
    }

}