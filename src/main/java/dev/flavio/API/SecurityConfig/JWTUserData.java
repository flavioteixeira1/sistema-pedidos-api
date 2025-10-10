package dev.flavio.API.SecurityConfig;

import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

@Builder
public record JWTUserData(Long userId, String email, String role) {
    
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // ✅ Agora usa o campo role que foi adicionado
        return List.of(new SimpleGrantedAuthority("ROLE_" + this.role));
    }
}

