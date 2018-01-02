package com.bsgfb.cdp.gc.runner;

import com.bsgfb.cdp.gc.model.Node;

import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Start {
    private static final Long OBJECTS_AMOUNT = 1024L;
    private static final Random RANDOM = new Random();

    public static void main(String[] args) {
        while (true) {
            List<Node> nodes = Stream
                    .generate(() -> new Node(RANDOM.nextInt(500) * 1024))
                    .limit(OBJECTS_AMOUNT)
                    .collect(toList());

            nodes.forEach(node -> node.setLinks(selectRandomly(nodes)));

            System.out.println("Sleep");
        }
    }

    static List<Node> selectRandomly(List<Node> nodes) {
        return nodes.stream().filter(node1 -> RANDOM.nextInt(100) > 50).collect(toList());
    }
}
