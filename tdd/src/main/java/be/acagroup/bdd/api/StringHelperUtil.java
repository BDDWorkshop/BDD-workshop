package be.acagroup.bdd.api;

import java.util.ArrayList;
import java.util.List;

public class StringHelperUtil {

    public static boolean is(String a, String b) {
        String lowerA = a.toLowerCase();
        String lowerB = b.toLowerCase();

        List<Integer> positions = new ArrayList<>();

        int index = lowerA.indexOf(lowerB);
        while (index != -1) {
            positions.add(index);
            index = lowerA.indexOf(lowerB, index + 1);
        }

        System.out.println("Found at positions: " + positions);

        return !positions.isEmpty();
    }
}
