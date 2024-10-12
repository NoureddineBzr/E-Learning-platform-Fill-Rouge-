package com.elearning.repository;

import com.elearning.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<AppUser,Long> {
    Optional<AppUser> findByUsername(String userName);
    Optional<AppUser> findUserByEmail(String email);

}
