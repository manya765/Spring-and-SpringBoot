package com.example.demo.service;

import com.example.demo.entity.user;
import com.example.demo.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private userRepository Userrepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        user User = Userrepository.findUserByUserName(username);
        if (User!=null){
            UserDetails userdetails =org.springframework.security.core.userdetails.User.builder()
                    .username(User.getUserName())
                    .password(User.getPassword())
                    .roles(User.getRoles().toArray(new String[0]))
                    .build();

        }
        throw new UsernameNotFoundException("user not found "+ username);
    }
}
