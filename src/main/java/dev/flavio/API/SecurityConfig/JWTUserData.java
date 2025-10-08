package dev.flavio.API.SecurityConfig;

import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

@Builder
public record JWTUserData(Long userId, String email) {
    
    // Método para obter authorities (roles)
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // ✅ Ajuste conforme as roles do seu sistema
        // Por exemplo, se você tiver roles no banco:
        // return Arrays.stream(roles.split(","))
        //        .map(role -> new SimpleGrantedAuthority("ROLE_" + role.trim()))
        //        .collect(Collectors.toList());
        
        // ✅ Por enquanto, retorne uma role padrão
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }
}

