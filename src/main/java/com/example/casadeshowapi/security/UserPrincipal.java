package com.example.casadeshowapi.security;

import java.util.Collection;
import java.util.Collections;

import com.example.casadeshowapi.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;



public class UserPrincipal implements UserDetails {

    private User user;

    public UserPrincipal(User user) {
        super();
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + user.getRole());
        return Collections.singleton(authority);

    }

    @Override
    public String getPassword() {
        return user.getSenha();
    }

    @Override
    public String getUsername() {
        return user.getNome();
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }

}