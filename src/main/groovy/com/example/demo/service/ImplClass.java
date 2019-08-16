package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public class ImplClass implements ExtendInterface {
    @Override
    public ExtendInterface getInstance() {
        return null;
    }

    @Override
    public ExtendInterface accept() {
        return null;
    }
}
