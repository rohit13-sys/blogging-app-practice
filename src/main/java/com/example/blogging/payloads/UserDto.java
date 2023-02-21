package com.example.blogging.payloads;

import lombok.*;

import javax.validation.constraints.*;


public class UserDto {

    @NotEmpty
    @Size(min = 4,message = "Name should be more than 4 characters")
    private String name;

    @Email
    private String email;

    @Size(min = 4,max = 10,message = "password should be more than 4 characters and less than 20")
    @NotEmpty
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$")
    private String password;

    @Size(min = 4,message = "about should be more than 4 characters")
    @NotEmpty
    private String about;

    public String getName() {
        return name;
    }

    public UserDto(String name, String email, String password, String about) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.about = about;
    }

    public UserDto(){

    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", about='" + about + '\'' +
                '}';
    }
}
