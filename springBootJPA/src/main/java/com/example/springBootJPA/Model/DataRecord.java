package com.example.springBootJPA.Model;

import lombok.Data;

import java.util.HashMap;

public @Data
class DataRecord {
    private Integer lineNumber;
    private HashMap<String, String> dataMap;

}
