package dev.flavio.API.Entity;

public enum UserRole {
    
    ADMIN("admin"),
    SUPERVISOR("supervisor"),
    INFRA("infra"),
    USER("user");


    private final String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
