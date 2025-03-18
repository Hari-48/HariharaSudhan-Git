package com.example.Hari.Controller;

import com.example.Hari.Model.Product;
import com.example.Hari.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;


   // public record Products(int id, String name , String prize){}

    @RequestMapping ("/get")
    public List<Product> getProducts(){
        return productService.getProducts();
    }


}
