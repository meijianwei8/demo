package com.example.demo.service;

public interface ExtendInterface extends ExtendableInterface<ExtendInterface> {

    ExtendInterface getInstance();

    @Override
    default ExtendInterface accept() {
        return null;
    }

    static ExtendInterface allow(){
        return null;
    }

}
