package com.sudhan.SpringSecurity.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;




@Entity
@Getter
@Setter
@Table(name = "users")
public class Users {
    @Id
    public Integer id;
    public String username;
    public String password;
}
