package com.example.demo.util;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@Data
public abstract class Treeable<T> {
    private List<T> childTree;

    abstract boolean connection(T parent);

    boolean root(T root){
        return this.equals(root);
    }
}
