package org.anita.adventofcode.year2018;

import java.util.*;

public class Day8 {

    public int task1(Iterator<Integer> stream) {
        Node root = readNode(stream);
        int totalSum = 0;
        Deque<Node> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node node = stack.poll();
            totalSum += node.metadata.stream().mapToInt(i -> i).sum();
            for (Node child : node.children) {
                stack.push(child);
            }
        }
        return totalSum;
    }

    public int task2(Iterator<Integer> stream) {
        Node root = readNode(stream);
        int sum = 0;
        Deque<Node> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node node = stack.poll();
            if (node.children.isEmpty()) {
                sum += node.metadata.stream().mapToInt(i -> i).sum();
            } else {
                for (Integer metadata : node.metadata) {
                    int index = metadata - 1;
                    if (index >= 0 && index < node.children.size()) {
                        stack.push(node.children.get(index));
                    }
                }
            }
        }
        return sum;
    }

    private Node readNode(Iterator<Integer> stream) {
        Node node = new Node();
        int childrenNum = stream.next();
        int metadataNum = stream.next();
        for (int i = 0; i < childrenNum; ++i) {
            node.children.add(readNode(stream));
        }
        for (int i = 0; i < metadataNum; ++i) {
            int metadata = stream.next();
            node.metadata.add(metadata);
        }
        return node;
    }

    public static class Node {
        List<Node> children = new ArrayList<>();
        List<Integer> metadata = new ArrayList<>();
    }
}
