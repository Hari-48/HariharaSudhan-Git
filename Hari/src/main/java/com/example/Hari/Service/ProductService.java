package com.example.Hari.Service;

import com.example.Hari.Model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ProductService {

    List<Product> products = Arrays.asList(new Product(100,"Iphone","1.5L"),
            new Product(101,"samsung","1L"));

    public List<Product> getProducts(){
        return products;
    }
}
