package com.example.demo.util;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TreeUtils<T extends Treeable<T>> {

    public List<T> toTreeWithRoot(Iterable<T> originalList, T root){
        Iterator<T> iterator = originalList.iterator();
        List<T> parentList = new ArrayList<>();
        while (iterator.hasNext()) {
            T t = iterator.next();
            if (t.root(root)) {
                parentList.add(t);
                iterator.remove();
            }
        }
        toTreeChild(parentList,originalList);
        return parentList;
    }

    private void toTreeWithOutRoot(Iterable<T> original){
        List<T> parentList = new ArrayList<>();
        for (T current : original) {
            findParentList(current, parentList, original);
        }
        Iterator<T> iterator = original.iterator();
        while (iterator.hasNext()) {
            T next = iterator.next();
            if (parentList.contains(next)) {
                iterator.remove();
            }
        }
        toTreeChild(parentList, original);
    }

    private void findParentList(T current, List<T> parentList, Iterable<T> original){
        for (T t : original) {
            if (current == t) {
                continue;
            }
            if (current.connection(t)) {
                current = t;
                findParentList(current,parentList,original);
                break;
            }
        }
        parentList.add(current);
    }

    private void toTreeChild(List<T> parentList ,Iterable<T> originalList){
        for (T t : parentList) {
            List<T> childList = new ArrayList<>();
            t.setChildTree(childList);
            Iterator<T> iterator = originalList.iterator();
            while (iterator.hasNext()) {
                T child = iterator.next();
                if (child.connection(t)) {
                    childList.add(child);
                    iterator.remove();
                }
            }
            toTreeChild(childList,originalList);
        }
    }


}
