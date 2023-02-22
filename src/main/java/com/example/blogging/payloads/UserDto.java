package com.example.blogging.payloads;

import lombok.*;

import javax.validation.constraints.*;

@Getter
@Setter
public class UserDto {

    private int id;
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


}
