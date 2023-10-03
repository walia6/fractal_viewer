package com.andrewalia.model;

import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

public class SierpinskiTriangle implements Fractal {

    @Override
    public void generateFractal(PixelWriter pixelWriter, int width, int height, double viewportX, double viewportY, double viewportHeight, int maxIterations) {
        for (int px = 0; px < width; px++) {
            for (int py = 0; py < height; py++) {
                if (isInSierpinskiTriangle(px, py, maxIterations)) {
                    pixelWriter.setColor(px, py, Color.BLACK);
                } else {
                    pixelWriter.setColor(px, py, Color.WHITE);
                }
            }
        }
    }

    private boolean isInSierpinskiTriangle(int x, int y, int iterations) {
        for (int i = 0; i < iterations; i++) {
            if (x % 3 == 1 && y % 3 == 1) {
                return false;
            }
            x /= 3;
            y /= 3;
        }
        return true;
    }
}
