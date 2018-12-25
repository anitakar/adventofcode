package org.anita.adventofcode.year2018;

import org.anita.adventofcode.structures.Point4D;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Day25 {

    public int task1(List<Point4D> points) {
        int numberOfCliques = 0;
        while (!points.isEmpty()) {
            numberOfCliques += 1;
            List<Point4D> clique = new ArrayList<>();
            clique.add(points.remove(0));
            enlargeClique(clique, points);
        }
        return numberOfCliques;
    }

    private void enlargeClique(List<Point4D> clique, List<Point4D> points) {
        int prevCliqueSize = clique.size();
        Iterator<Point4D> pointsIterator = points.iterator();
        while (pointsIterator.hasNext()) {
            Point4D candidate = pointsIterator.next();
            boolean add = false;
            for (Point4D cliquePoint : clique) {
                if (candidate.manhattanDistance(cliquePoint) <= 3) {
                    add = true;
                }
            }
            if (add) {
                clique.add(candidate);
                pointsIterator.remove();
            }
        }
        int newCliqueSize = clique.size();
        if (newCliqueSize > prevCliqueSize) {
            enlargeClique(clique, points);
        }
    }
}
