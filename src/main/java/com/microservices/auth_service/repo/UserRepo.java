package com.microservices.auth_service.repo;

import com.microservices.auth_service.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<Users, Long> {
    public Users findByUsername(String username);
    public Users findByEmail(String email);
}