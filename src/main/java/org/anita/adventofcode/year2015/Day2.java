package org.anita.adventofcode.year2015;

import java.security.MessageDigest;
import java.util.List;

public class Day2 {

    private MessageDigest md;

    public int wrapping(List<int[]> dims) {
        int totalWrapping = 0;
        for (int[] dim : dims) {
            if (dim[2] < dim[1]) {
                swap(dim, 2, 1);
            }
            if (dim[1] < dim[0]) {
                swap(dim, 1, 0);
            }
            if (dim[2] < dim[1]) {
                swap(dim, 2, 1);
            }
            totalWrapping += 3 * dim[0] * dim[1] + 2 * dim[1] * dim[2] + 2 * dim[0] * dim[2];
        }
        return totalWrapping;
    }

    public int ribbon(List<int[]> dims) {
        int totalRibbon = 0;
        for (int[] dim : dims) {
            if (dim[2] < dim[1]) {
                swap(dim, 2, 1);
            }
            if (dim[1] < dim[0]) {
                swap(dim, 1, 0);
            }
            if (dim[2] < dim[1]) {
                swap(dim, 2, 1);
            }
            totalRibbon += dim[0] * dim[1] * dim[2] + 2 * dim[0] + 2 * dim[1];
        }
        return totalRibbon;
    }

    private int[] swap(int[] toSwap, int x, int y) {
        int temp = toSwap[x];
        toSwap[x] = toSwap[y];
        toSwap[y] = temp;
        return toSwap;
    }
}