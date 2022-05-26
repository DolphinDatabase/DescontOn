package com.dev.ecommerce.utils;


public class Util {
    
    public Double converterDouble(String valor){
        String recebe = valor.replaceAll("[\\D]", "");
        
        return Double.parseDouble(recebe);
    }

    public Integer converterInteger(String valor){
        String recebe = valor.replaceAll("[\\D]", "");
        return Integer.parseInt(recebe);
    }

}
