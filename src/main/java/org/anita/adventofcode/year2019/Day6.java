package org.anita.adventofcode.year2019;

import java.util.*;

public class Day6 {

    private HashMap<String, SpaceObject> planetSystem = new HashMap<>();

    public int task1(List<String> relations) {
        parse(relations);

        int totalDistance = 0;
        SpaceObject com = planetSystem.get("COM");
        Deque<SpaceObject> toVisit = new LinkedList<>();
        toVisit.add(com);
        while (!toVisit.isEmpty()) {
            SpaceObject cur = toVisit.pop();
            for (SpaceObject child : cur.children) {
                child.setDistanceFromCom(cur.distanceFromCom + 1);
                totalDistance += child.distanceFromCom;
                toVisit.add(child);
            }
        }

        return totalDistance;
    }

    public int task2(List<String> relations) {
        parse(relations);

        String santaPath = rootPath(planetSystem.get("SAN"));
        String youPath = rootPath(planetSystem.get("YOU"));

        int i = 0;
        for (; i < youPath.length(); ++i) {
            if (youPath.charAt(i) != santaPath.charAt(i)) {
                break;
            }
        }

        int youSteps = 0;
        for (int j = i; j < youPath.length(); ++j) {
            if (youPath.charAt(j) == '|') {
                youSteps += 1;
            }
        }

        int santaSteps = 0;
        for (int j = i; j < santaPath.length(); ++j) {
            if (santaPath.charAt(j) == '|') {
                santaSteps += 1;
            }
        }

        return youSteps + santaSteps;
    }

    private String rootPath(SpaceObject obj) {
        StringBuilder path = new StringBuilder();
        SpaceObject cur = obj;
        while (cur != null) {
            path.append(cur.name).append("|");
            cur = cur.parent;
        }
        return path.reverse().toString();
    }

    private void parse(List<String> relations) {
        for (String relation : relations) {
            String[] objs = relation.split("\\)");
            SpaceObject parent = planetSystem.getOrDefault(objs[0], new SpaceObject(objs[0], null));
            SpaceObject child = planetSystem.getOrDefault(objs[1], new SpaceObject(objs[1], parent));
            child.setParent(parent);
            parent.addChild(child);
            planetSystem.put(parent.name, parent);
            planetSystem.put(child.name, child);
        }
    }

    private static final class SpaceObject {
        private final String name;
        private SpaceObject parent;
        private List<SpaceObject> children = new LinkedList<>();
        private int distanceFromCom = 0;

        SpaceObject(String name, SpaceObject parent) {
            this.name = name;
            this.parent = parent;
        }

        public void addChild(SpaceObject child) {
            children.add(child);
        }

        public void setParent(SpaceObject parent) {
            this.parent = parent;
        }

        public void setDistanceFromCom(int distanceFromCom) {
            this.distanceFromCom = distanceFromCom;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            SpaceObject that = (SpaceObject) o;
            return name.equals(that.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }
}
