package com.sudhan.SpringSecurity.Repository;

import com.sudhan.SpringSecurity.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyUserRepo extends JpaRepository<Users,Integer> {
    Users findByUsername(String username);
}
