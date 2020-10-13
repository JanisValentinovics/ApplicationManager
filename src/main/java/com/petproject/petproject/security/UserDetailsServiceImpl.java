package com.petproject.petproject.security;

import com.petproject.petproject.model.Roles;
import com.petproject.petproject.model.Status;
import com.petproject.petproject.model.User;
import com.petproject.petproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service("userDetailServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(s).orElseThrow(()-> new UsernameNotFoundException("User not found"));
        return SecurityUser.fromUser(user);
    }

    public void saveSimpleUser(User user) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(Roles.USER);
        user.setStatus(Status.ACTIVE);
        userRepository.save(user);
    }

    public Long getUserId(User user) {
        return user.getId();
    }

}
