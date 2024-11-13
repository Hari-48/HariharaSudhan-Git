package com.example.Hari.Batchprocessing.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "CUSTOMER_INFO")

public class Customer {



    @Id

    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
    private String job;
    private String email;
    private String gender;


}


