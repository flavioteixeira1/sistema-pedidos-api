package dev.flavio.API.SecurityConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.DispatcherType;
import org.springframework.http.HttpMethod;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final SecurityFilter securityFilter;

    public SecurityConfiguration(SecurityFilter securityFilter){
        this.securityFilter = securityFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                    .dispatcherTypeMatchers(DispatcherType.ERROR).permitAll()
                    // ✅ Endpoints públicos (autenticação)
                    .requestMatchers(HttpMethod.POST, "/auth/logar").permitAll()
                    .requestMatchers(HttpMethod.POST, "/auth/registrar").permitAll()
                    
                    // ✅ Endpoints para ADMIN , SUPERVISOR ou INFRA
                   // .requestMatchers(HttpMethod.GET,"/teste").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.POST, "/cliente/save").hasAnyRole("ADMIN", "SUPERVISOR")
                    .requestMatchers(HttpMethod.PUT, "/cliente/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/cliente/**").hasAnyRole("ADMIN", "INFRA")
                    
                    .requestMatchers(HttpMethod.POST, "/produto/save").hasAnyRole("ADMIN","SUPERVISOR", "INFRA")
                    .requestMatchers(HttpMethod.PUT, "/produto/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/produto/**").hasAnyRole("ADMIN", "SUPERVISOR", "INFRA")
                    
                    // ✅ Endpoints para USER (criação, alteração parcial e deleção de pedidos)
                    .requestMatchers(HttpMethod.POST, "/pedido/**").hasAnyRole("USER", "ADMIN", "SUPERVISOR")
                    .requestMatchers(HttpMethod.PUT, "/pedido/**").hasAnyRole("USER", "ADMIN", "SUPERVISOR")
                    .requestMatchers(HttpMethod.PATCH, "/pedido/**").hasAnyRole("USER", "ADMIN","SUPERVISOR")
                    .requestMatchers(HttpMethod.DELETE, "/pedido/**").hasAnyRole("USER", "ADMIN", "SUPERVISOR")
                    
                    // ✅ Endpoints de consulta para todos os usuários autenticados
                    .requestMatchers(HttpMethod.GET, "/**").hasAnyRole("USER", "ADMIN", "SUPERVISOR", "INFRA")
                    
                    // ✅ Todos os outros endpoints exigem autenticação
                    .anyRequest().authenticated())
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();    
    }


    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOriginPatterns(List.of("*")); // Permite todas as origens (ajuste para produção)
    configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
    configuration.setAllowedHeaders(List.of("*"));
    configuration.setAllowCredentials(true);
    
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
}

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}