package pl.konczak.etest.util;

import java.util.Map;
import java.util.Random;

public class UnusedMapKeySearchUtil {

    public static int findUnusedRandomKey(Random generator, int randomMax, Map map) {
        int id;
        do {
            id = generator.nextInt(randomMax);
        } while (map.containsKey(id));

        return id;
    }
}
