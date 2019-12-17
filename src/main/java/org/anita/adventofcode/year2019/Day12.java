package org.anita.adventofcode.year2019;

import java.util.List;

public class Day12 {

    public long task1(List<Moon> moons, int steps) {
        for (int i = 0; i < steps; ++i) {

            for (Moon moon : moons) {
                int diffX1 = 0;
                int diffX2 = 0;
                int diffX3 = 0;
                for (Moon other : moons) {
                    if (moon.id != other.id) {
                        if (moon.x1 < other.x1) {
                            diffX1 += 1;
                        } else if (moon.x1 > other.x1) {
                            diffX1 -= 1;
                        }
                        if (moon.x2 < other.x2) {
                            diffX2 += 1;
                        } else if (moon.x2 > other.x2) {
                            diffX2 -= 1;
                        }
                        if (moon.x3 < other.x3) {
                            diffX3 += 1;
                        } else if (moon.x3 > other.x3) {
                            diffX3 -= 1;
                        }
                        moon.updateVelocity(diffX1, diffX2, diffX3);
                    }
                    diffX1 = 0;
                    diffX2 = 0;
                    diffX3 = 0;
                }
            }

            for (Moon moon : moons) {
                moon.updatePosition();
            }
        }


        long totalEnergy = 0;
        for (Moon moon : moons) {
            totalEnergy += moon.totalEnergy();
        }
        return totalEnergy;
    }

    public static class Moon {
        public int id;
        public int x1, x2, x3;
        public int v1 = 0, v2 = 0, v3 = 0;

        public Moon(int id, int x1, int x2, int x3) {
            this.id = id;
            this.x1 = x1;
            this.x2 = x2;
            this.x3 = x3;
        }

        public void updateVelocity(int diffX1, int diffX2, int diffX3) {
            v1 += diffX1;
            v2 += diffX2;
            v3 += diffX3;
        }

        public void updatePosition() {
            x1 += v1;
            x2 += v2;
            x3 += v3;
        }

        public int potentialEnergy() {
            return Math.abs(x1) + Math.abs(x2) + Math.abs(x3);
        }

        public int kinematicEnergy() {
            return Math.abs(v1) + Math.abs(v2) + Math.abs(v3);
        }

        public int totalEnergy() {
            return potentialEnergy() * kinematicEnergy();
        }
    }
}
