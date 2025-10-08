package dev.flavio.API.dto.Request;

import jakarta.validation.constraints.NotEmpty;

public record RegisterUserRequest(
    @NotEmpty(message = "username é obrigatório")String name, 
    @NotEmpty(message = "email obrigatório")String email, 
     @NotEmpty(message = "senha é obrigatória")String password)
     {

}
