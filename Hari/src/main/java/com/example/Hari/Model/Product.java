package com.example.Hari.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class Product {
    private int productId;
    private String productName;
    private String prize;
}
