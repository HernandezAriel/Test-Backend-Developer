package com.testbackend.test.model.enums;

public enum DocumentType {
    DNI("DNI"), LE("LE"), LC("LC");
    private final String a;
    DocumentType(String value){
        this.a = value;
    }

    public String getValue(){
        return a;
    }
}
