package dev.flavio.API.dto.Request;

import dev.flavio.API.Entity.UserRole;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record RegisterUserRequest(
    @NotEmpty(message = "username é obrigatório") String name, 
    @NotEmpty(message = "email obrigatório") String email, 
    @NotEmpty(message = "senha é obrigatória") String password,
    @NotNull(message = "role é obrigatória") UserRole role
) {}