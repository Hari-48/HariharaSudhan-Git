package com.example.springBootJPA.Controller;

import com.example.springBootJPA.CriteriaBuilder.UserRepo;
import com.example.springBootJPA.Entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@Slf4j
public class UserController {
    @Autowired
   private UserRepo userRepo;

    @GetMapping("criteriaBuilder/{name}/getByName")
    public ResponseEntity<?> findByName(@PathVariable String name){
        List<User> afcs = userRepo.findByName(name);
        log.info("Users:{}",afcs);
        return ResponseEntity.status(HttpStatus.OK).body(afcs);
    }

    @GetMapping("criteriaBuilder/{name}/{role}/getByNameAndRole")
    public ResponseEntity<?> findByNameOrRole(@PathVariable String name,@PathVariable String role){
        List<User> afcs = userRepo.findByNameAndRoles(name,role);
        log.info("Users:{}",afcs);
        return ResponseEntity.status(HttpStatus.OK).body(afcs);

    }
}
