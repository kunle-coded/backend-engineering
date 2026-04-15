package model;

import java.util.Optional;

import core.BaseUser;

public class User extends BaseUser {
    private String username;
    private String email;
    private String password;
    private Role role;

    // Guest users
    public User(String email, Role role){
        this.email = email;
        this.role = role;
    }

    // Admin and Regular users
    public User(String email, String password, Role role){
        this(email, role);
        this.password = password;
    }

    // Getters
    public String getUsername(){
        return this.username;
    }

    public String getEmail(){
        return this.email;
    }

    public Role getRole(){
        return this.role;
    }

    public Optional<String> getPassword(){
        return Optional.ofNullable(this.password);
    }

    // Setters
    public void setRole(Role role){
        this.role = role;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setUsername(String username){
        this.username = username;
    }

    @Override
    public String toString(){
        return String.format("User [ID: %s | Email: %s | Role: %s]", getId(), email, role);
    }
}
