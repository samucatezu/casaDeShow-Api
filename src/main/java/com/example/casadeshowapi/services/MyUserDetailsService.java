package com.example.casadeshowapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.casadeshowapi.entities.User;
import com.example.casadeshowapi.repository.UserRepository;
import com.example.casadeshowapi.security.UserPrincipal;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String nome) throws UsernameNotFoundException {

        User user = repo.findByNome(nome);
        if(user==null) {
            throw new UsernameNotFoundException("User inexistente");
        }

        return new UserPrincipal(user);
    }

}
