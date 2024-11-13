package com.example.Hari.Batchprocessing.Config;

import com.example.Hari.Batchprocessing.Entity.Customer;

import org.springframework.batch.item.ItemProcessor;

public class CustomProcessor implements ItemProcessor<Customer,Customer> {

    @Override
    public Customer process(Customer customer) throws Exception{
        return customer;
//        if(customer.getGender().equals("Male")) {
//            return customer;
//        }
//        else {
//            return null;
//        }
    }
}
