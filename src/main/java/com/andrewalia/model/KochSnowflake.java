package com.andrewalia.model;

import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

public class KochSnowflake implements Fractal {
    
    @Override
    public void generateFractal(PixelWriter pixelWriter, int width, int height, double viewportX, double viewportY, double viewportHeight, int maxIterations) {
        System.out.println("Koch Loaded");
        double viewportWidth = viewportHeight * (double) width / height;
        double minX = viewportX - viewportWidth / 2.0;
        double maxX = viewportX + viewportWidth / 2.0;
        double minY = viewportY - viewportHeight / 2.0;
        double maxY = viewportY + viewportHeight / 2.0;

        for (int px = 0; px < width; px++) {
            for (int py = 0; py < height; py++) {
                double x = minX + (maxX - minX) * px / (width - 1);
                double y = minY + (maxY - minY) * py / (height - 1);
                
                if (inKochSnowflake(x, y, maxIterations)) {
                    pixelWriter.setColor(px, py, Color.BLACK);
                } else {
                    pixelWriter.setColor(px, py, Color.WHITE);
                }
            }
        }
    }

    private boolean inKochSnowflake(double x, double y, int iterations) {
        // Convert (x, y) to barycentric coordinates
        double a1 = (0.5 * (1 - x) + 0.5 * (y - 1)) / (0.5);
        double a2 = (0.5 * (2 * x - 1) + 0.5 * (1 - y)) / (0.5);
        double a3 = 1 - a1 - a2;

        // Convert to triangular coordinates
        double u = a1;
        double v = a2;
        double w = a3;

        // Iterate to check for escape condition
        for (int i = 0; i < iterations; i++) {
            if (u >= 0.5 && u <= 1 && v >= 0 && v <= 0.5) {
                u = 2 * u - 1;
                v = 2 * v;
                w = 2 * w;
            } else if (v >= 0.5 && v <= 1 && w >= 0 && w <= 0.5) {
                u = 2 * u;
                v = 2 * v - 1;
                w = 2 * w;
            } else if (w >= 0.5 && w <= 1 && u >= 0 && u <= 0.5) {
                u = 2 * u;
                v = 2 * v;
                w = 2 * w - 1;
            } else {
                return false;
            }
        }
        return true;
    }
}
