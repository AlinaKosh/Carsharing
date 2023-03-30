package com.project.carshar.services;

public interface ToUpperCase {
    default String upper(String str){
        String cap = str.substring(0, 1).toUpperCase() + str.substring(1);
        return cap;
    }
}
