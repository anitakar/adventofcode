package org.anita.adventofcode.year2019;

import java.util.Arrays;

public class Day8 {

    public int task1(String image, int width, int height) {
        int layerSize = width * height;
        int minimalNumberOfZeros = Integer.MAX_VALUE;
        int minimalLayerControlSum = 0;
        int numberOfZeros = Integer.MAX_VALUE;
        int numberOfOnes = Integer.MAX_VALUE;
        int numberOfTwos = Integer.MAX_VALUE;
        char[] chars = image.toCharArray();
        for (int i = 0; i < chars.length; ++i) {
            if (i % layerSize == 0) {
                if (numberOfZeros < minimalNumberOfZeros) {
                    minimalNumberOfZeros = numberOfZeros;
                    minimalLayerControlSum = numberOfOnes * numberOfTwos;
                }
                numberOfZeros = 0;
                numberOfOnes = 0;
                numberOfTwos = 0;
            }
            if (chars[i] == '0') {
                numberOfZeros++;
            } else if (chars[i] == '1') {
                numberOfOnes++;
            } else if (chars[i] == '2') {
                numberOfTwos++;
            }
        }
        return minimalLayerControlSum;
    }

    public void task2(String image, int width, int height) {
        int[] decodedImage = new int[height * width];
        Arrays.fill(decodedImage, 2);
        int layerSize = width * height;
        char[] chars = image.toCharArray();
        for (int i = 0; i < chars.length; ++i) {
            int pos = i % layerSize;
            if (decodedImage[pos] == 2) {
                decodedImage[pos] = chars[i] - '0';
            }
        }

        for (int h = 0; h < height; ++h) {
            for (int w = 0; w < width; ++w) {
                int pixel = decodedImage[h * width + w];
                if (pixel == 2) {
                    System.out.print(" ");
                } else if (pixel == 1) {
                    System.out.print("X");
                } else if (pixel == 0) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}
