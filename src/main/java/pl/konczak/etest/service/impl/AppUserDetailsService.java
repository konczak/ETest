package pl.konczak.etest.service.impl;

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

import pl.konczak.etest.entity.RoleEntity;
import pl.konczak.etest.entity.UserEntity;
import pl.konczak.etest.repository.IUserRepository;

@Service("userDetailsService")
public class AppUserDetailsService
        implements UserDetailsService {

    @Autowired
    private IUserRepository userRepository;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                username, user.getPassword(),
                true, true, true, true,
                grantAuthorities(user));

        return userDetails;
    }

    private List<GrantedAuthority> grantAuthorities(UserEntity user) {
        List<GrantedAuthority> grantedAuthoritys = new ArrayList<GrantedAuthority>();
        for (RoleEntity role : user.getRoles()) {
            grantedAuthoritys.add(new SimpleGrantedAuthority(role.getName()));
        }

        return grantedAuthoritys;
    }
}
