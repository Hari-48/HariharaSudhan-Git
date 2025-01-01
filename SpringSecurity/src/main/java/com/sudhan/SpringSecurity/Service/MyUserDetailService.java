package com.sudhan.SpringSecurity.Service;

import com.sudhan.SpringSecurity.Entity.UserPrincipal;
import com.sudhan.SpringSecurity.Entity.Users;
import com.sudhan.SpringSecurity.Repository.MyUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private MyUserRepo myUserRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = myUserRepo.findByUsername(username);
        if(user==null) {
            System.out.println("User Name is Not found ");
            throw new UsernameNotFoundException("Username is not found");
        }

        return new UserPrincipal(user);

    }
}
