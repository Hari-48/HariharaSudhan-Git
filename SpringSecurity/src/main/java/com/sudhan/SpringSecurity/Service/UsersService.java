package com.sudhan.SpringSecurity.Service;

import com.sudhan.SpringSecurity.Entity.Users;
import com.sudhan.SpringSecurity.Repository.MyUserRepo;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {

    @Autowired
    private  MyUserRepo myUserRepo;
    @Autowired
    private JWTService jwtService;


    @Autowired
    private AuthenticationManager authenticationManager;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);


    public Users register(Users users) {
        users.setPassword(encoder.encode(users.getPassword()));
        return  myUserRepo.save(users);
    }

    public List<Users> getUser() {
        return myUserRepo.findAll();
    }

    public String verify(Users user) {

        Authentication authentication = authenticationManager.
                authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));

      if(authentication.isAuthenticated())
          return jwtService.generateToken(user.getUsername());

      return "Failed";
    }
}
