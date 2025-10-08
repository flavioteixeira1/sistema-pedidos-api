package dev.flavio.API.Controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dev.flavio.API.dto.Request.LoginRequest;
import dev.flavio.API.dto.Request.RegisterUserRequest;
import dev.flavio.API.dto.Response.LoginResponse;
import dev.flavio.API.dto.Response.RegisterUserResponse;
import jakarta.validation.Valid;
import dev.flavio.API.Entity.User;
import dev.flavio.API.Repository.UserRepository;
import dev.flavio.API.SecurityConfig.TokenConfig;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenConfig tokenConfig;

    public AuthController(UserRepository repository, PasswordEncoder passwordEncoder, 
    AuthenticationManager authenticationManager, TokenConfig tokenConfig){
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenConfig = tokenConfig;
    }

    

    @PostMapping("/logar")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest){
    System.out.println("Tentando autenticar: " + loginRequest.email());
    
    UsernamePasswordAuthenticationToken userAndPass = 
        new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password());
    Authentication authentication = authenticationManager.authenticate(userAndPass);
    User newUser = (User) authentication.getPrincipal();
    String token = tokenConfig.generateToken(newUser); 
    
    System.out.println("Token gerado para: " + newUser.getEmail());
    return ResponseEntity.ok(new LoginResponse(token));
}

    @PostMapping("/registrar")
    public ResponseEntity<RegisterUserResponse> register (@Valid @RequestBody RegisterUserRequest request){
        User newUser = new User();
        newUser.setPassword(passwordEncoder.encode(request.password()));
        newUser.setEmail(request.email());
        newUser.setName(request.name());
        newUser.setRole(request.role());
        repository.save(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(new RegisterUserResponse(newUser.getName(), newUser.getEmail()));
        
    }
}
