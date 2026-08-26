package com.generalassembly.gc;

import java.util.ArrayList;
import java.util.List;

public class OOMError {
    public static void main(String[] args) {
        List<String[]> list = new ArrayList<>();
        for (int i = 0; ; i++) {
            Runtime runtime = Runtime.getRuntime();
            System.out.printf("Iteration: %d total memory: %d  free memory: %d%n", i, runtime.totalMemory(), runtime.freeMemory());
            list.add(new String[10_000_000]);
        }
    }

    public void garbageCollection() {
        for (int i = 0; i < 100; i++) {
            String message = "This is message " + i;
            System.out.println(message);
        }
    }
}
