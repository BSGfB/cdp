package com.bsgfb.cdp.gc.model;

import java.util.Arrays;
import java.util.List;

public class Node {
    private byte[] array;
    private List<Node> links;

    public Node(int byteSize) {
        this.array = new byte[byteSize];
        Arrays.fill(this.array, Byte.MAX_VALUE);
    }

    public List<Node> getLinks() {
        return links;
    }

    public void setLinks(final List<Node> links) {
        this.links = links;
    }
}
