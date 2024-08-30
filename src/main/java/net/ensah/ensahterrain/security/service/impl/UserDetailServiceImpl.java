package net.ensah.ensahterrain.security.service.impl;

import net.ensah.ensahterrain.security.Repository.UserRepository;
import net.ensah.ensahterrain.security.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmailIgnoreCase(email);
        if (user == null) {
            throw new UsernameNotFoundException("Cet utilisateur n'existe pas");
        }

        String[] roleNames = user.getRoles().stream()
                .map(role -> role.getRoleName())
                .toArray(String[]::new);


        UserDetails build = org.springframework.security.core.userdetails.User.withUsername(email)
                .password(user.getPassword())
                .roles(roleNames)
                .build();
        return build;
    }

}
