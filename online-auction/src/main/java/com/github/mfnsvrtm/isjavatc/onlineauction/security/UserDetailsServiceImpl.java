package com.github.mfnsvrtm.isjavatc.onlineauction.security;

import com.github.mfnsvrtm.isjavatc.onlineauction.dao.UserDao;
import com.github.mfnsvrtm.isjavatc.onlineauction.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDao userDao;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userEntity = userDao.findByUsername(username).orElseThrow(() ->
            new UsernameNotFoundException("No user with username '%s' found.".formatted(username))
        );
        return new org.springframework.security.core.userdetails.User(
            userEntity.getUsername(),
            userEntity.getPassword(),
            userEntity.getGroups().stream().map(RoleAuthority::new).toList()
        );
    }

}
