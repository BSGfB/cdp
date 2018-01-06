package com.bsgfb.cdp.gc.runner;

import com.bsgfb.cdp.gc.model.Node;

import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Start {
    private static final Random RANDOM = new Random();

    public static void main(String[] args) {
        Long objectsNumber = Long.valueOf(System.getProperty("gc.objectsNumber", "1024"));
        System.out.println("Objects number are [" + objectsNumber + "]");
        Integer times = Integer.valueOf(System.getProperty("gc.times", "10"));


        for (int i = 0; i < times; i++) {
            System.out.println("Iteration number: " + i);
            List<Node> nodes = Stream
                    .generate(() -> new Node(RANDOM.nextInt(800) * 1024))
                    .limit(objectsNumber)
                    .collect(toList());

            nodes.forEach(node -> node.setLinks(selectRandomly(nodes)));
        }
    }

    static List<Node> selectRandomly(List<Node> nodes) {
        return nodes.stream().filter(node1 -> RANDOM.nextInt(100) > 50).collect(toList());
    }
}
