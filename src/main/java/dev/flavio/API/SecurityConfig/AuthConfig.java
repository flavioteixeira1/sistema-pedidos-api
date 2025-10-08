package dev.flavio.API.SecurityConfig;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import dev.flavio.API.Repository.UserRepository;


@Service
public class AuthConfig implements UserDetailsService {

    private final UserRepository usuarioRepository;

    public AuthConfig(UserRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        return usuarioRepository.findUserByEmail(username).orElseThrow(()-> new  UsernameNotFoundException(username));
    }

}
