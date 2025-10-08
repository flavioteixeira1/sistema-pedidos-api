package dev.flavio.API.dto.Request;

import jakarta.validation.constraints.NotEmpty;

public record LoginRequest(@NotEmpty (message = "username não pode ser vazio") String email, 
                           @NotEmpty (message= "password não pode ser vazio")String password) {

}
