package com.example.blogging.repository;

import com.example.blogging.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserReposiory extends JpaRepository<Users,Integer> {
    Users findByEmail(String userName);
}
