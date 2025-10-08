package dev.flavio.API.Entity;

public enum UserRole {
    ADMIN("admin"),
    USER("user"),
    SUPERVISOR("supervisor"),
    INFRA("infra");

    private final String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}