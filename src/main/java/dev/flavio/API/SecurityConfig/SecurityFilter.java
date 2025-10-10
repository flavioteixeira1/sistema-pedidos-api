package dev.flavio.API.SecurityConfig;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.util.Strings;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import dev.flavio.API.SecurityConfig.JWTUserData;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter{

    private final TokenConfig tokenConfig;

    public SecurityFilter(TokenConfig tokenConfig){
        this.tokenConfig = tokenConfig;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException { 
    
        String authorizedHeader = request.getHeader("Authorization");

        System.out.println("🔍 Header Authorization recebido: " + authorizedHeader);
        
        if (!Strings.isEmpty(authorizedHeader) && authorizedHeader.startsWith("Bearer ")){
            String token = authorizedHeader.substring("Bearer ".length());
            System.out.println("🔍 Token extraído: " + token.substring(0, Math.min(20, token.length())) + "...");

            Optional<JWTUserData> optUser = tokenConfig.validateToken(token);
            
            if(optUser.isPresent()){
                JWTUserData userData = optUser.get();
                // ✅ Adicione authorities (ajuste conforme suas roles)
                UsernamePasswordAuthenticationToken authentication = 
                    new UsernamePasswordAuthenticationToken(
                        userData, 
                        null, 
                        List.of(new SimpleGrantedAuthority("ROLE_USER")) // Ajuste conforme necessário
                    );
                SecurityContextHolder.getContext().setAuthentication(authentication);
                System.out.println("✅ Usuário autenticado: " + userData.email());
            } else {
                System.out.println("❌ Token inválido ou expirado");
            }
        } else {
            System.out.println("ℹ️  Header Authorization ausente ou mal formatado: " + authorizedHeader);
        }
        
        // ✅ SEMPRE chamar o filterChain para continuar a cadeia de filtros
        filterChain.doFilter(request, response);
    }
}