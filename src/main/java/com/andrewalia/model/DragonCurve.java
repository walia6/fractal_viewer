package com.andrewalia.model;

import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

public class DragonCurve implements Fractal {

    @Override
    public void generateFractal(PixelWriter pixelWriter, int width, int height, double viewportX, double viewportY, double viewportHeight, int maxIterations) {
        double viewportWidth = viewportHeight * (double) width / height;
        double minX = viewportX - viewportWidth / 2.0;
        double maxX = viewportX + viewportWidth / 2.0;
        double minY = viewportY - viewportHeight / 2.0;
        double maxY = viewportY + viewportHeight / 2.0;

        double x = 0.0, y = 0.0;
        double dx = 1.0, dy = 0.0;
        double temp;

        for (int i = 0; i < Math.pow(2, maxIterations); i++) {
            int px = (int) ((x - minX) / (maxX - minX) * width);
            int py = (int) ((maxY - y) / (maxY - minY) * height);
            
            if (px >= 0 && px < width && py >= 0 && py < height) {
                pixelWriter.setColor(px, py, Color.BLACK);
            } else {
                pixelWriter.setColor(px, py, Color.WHITE);
            }

            if ((i & i - 1) % 4 == 0) {
                // Turn right
                temp = dx;
                dx = dy;
                dy = -temp;
            } else {
                // Turn left
                temp = dx;
                dx = -dy;
                dy = temp;
            }

            x += dx;
            y += dy;
        }
    }
}
