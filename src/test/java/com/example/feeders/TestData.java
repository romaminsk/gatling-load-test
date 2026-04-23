package com.example.feeders;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class TestData {
    private static final List<String> CITIES = Arrays.asList("Denver", "Frankfurt", "London", "Los Angeles", "Paris", "Portland", "San Francisco", "Seattle", "Sydney", "Zurich");
    private static final Random RANDOM = new Random();

    public static final Iterator<Map<String, Object>> CITY_FEEDER = Stream.generate((Supplier<Map<String, Object>>) () -> {
        String depart = CITIES.get(RANDOM.nextInt(CITIES.size()));
        String arrive;
        do { arrive = CITIES.get(RANDOM.nextInt(CITIES.size())); } while (depart.equals(arrive));

        Map<String, Object> map = new HashMap<>();
        map.put("departCity", depart);
        map.put("arriveCity", arrive);
        return map;
    }).iterator();
}