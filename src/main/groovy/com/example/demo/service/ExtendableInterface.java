package com.example.demo.service;

public interface ExtendableInterface<B extends ExtendableInterface<B>> {

    B accept();
}
