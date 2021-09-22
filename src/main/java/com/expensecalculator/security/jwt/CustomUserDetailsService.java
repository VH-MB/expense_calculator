package com.expensecalculator.security.jwt;

import com.expensecalculator.modules.user.User;
import com.expensecalculator.modules.user.UserQueryRepository;
import com.expensecalculator.modules.user.exeption.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserQueryRepository userQueryRepository;

    @Autowired
    public CustomUserDetailsService(UserQueryRepository userQueryRepository) {
        this.userQueryRepository = userQueryRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userQueryRepository.findUserByEmail(username)
                .orElseThrow(() -> new UserNotFoundException(username));

        return build(user);
    }

    public User loadUserById(Long id){
        return userQueryRepository.findUserById(id).orElseThrow(null);
    }

    public static User build(User user){
        List<GrantedAuthority> authorities = user.getRole().stream()
                .map(role -> new SimpleGrantedAuthority(role.name()))
                .collect(Collectors.toList());

        return new User(
                user.getIdUser(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                authorities);
    }
}
