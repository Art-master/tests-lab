package com.tests.lab;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class CollectionsUsage {

    public void main() {
        LinkedList<Integer> list = new LinkedList<>();
       // list.

        Vector<Integer> vector = new Vector<>();

        ArrayList<Integer> arrayList = new ArrayList<>();
        List<Integer> synchro = Collections.synchronizedList(arrayList);

        CopyOnWriteArrayList<Integer> copy = new CopyOnWriteArrayList<>();
    }
}
