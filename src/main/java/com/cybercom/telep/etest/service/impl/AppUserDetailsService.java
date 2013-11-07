package com.cybercom.telep.etest.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cybercom.telep.etest.entity.Role;
import com.cybercom.telep.etest.entity.User;
import com.cybercom.telep.etest.repository.IUserRepository;

@Service("userDetailsService")
public class AppUserDetailsService
        implements UserDetailsService {

    @Autowired
    private IUserRepository userRepository;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        System.out.println("sprawdzam <" + username + ">");
        User user = userRepository.findByEmail(username);

        if (user == null) {
            System.out.println("usern not found");
            throw new UsernameNotFoundException("User not found");
        }

        System.out.println("user found!");
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                username, user.getPassword(),
                true, true, true, true,
                grantAuthorities(user));

        return userDetails;
    }

    private List<GrantedAuthority> grantAuthorities(User user) {
        List<GrantedAuthority> grantedAuthoritys = new ArrayList<GrantedAuthority>();
        for (Role role : user.getRoles()) {
            grantedAuthoritys.add(new SimpleGrantedAuthority(role.getName()));
        }

        return grantedAuthoritys;
    }
}
