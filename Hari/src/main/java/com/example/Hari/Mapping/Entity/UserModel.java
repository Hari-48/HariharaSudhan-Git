package com.example.Hari.Mapping.Entity;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class UserModel {
    private long id;
    private String name ;
    private String firstName;
    private String lastName;
    private String role;
    private String gender;
}
