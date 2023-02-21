package com.example.blogging.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private int id;

    @Column(name = "user_name" ,nullable = false,length = 100)
    private String name;

    private String email;

    private String password;

    private String about;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Post> posts=new ArrayList<>();
}
