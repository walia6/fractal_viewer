package com.andrewalia.model;

import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

import java.util.Random;

public class Barnsley implements Fractal {

    @Override
    public void generateFractal(PixelWriter pixelWriter, int width, int height, double viewportX, double viewportY, double viewportHeight, int maxIterations) {
        double x = 0, y = 0;
        Random random = new Random();

        // Scaling and translation factors for viewport
        double scaleX = width / 10.0;
        double scaleY = height / 10.0;
        double offsetX = width / 2.0 + viewportX * scaleX;
        double offsetY = height - viewportY * scaleY;

        // Initialize all pixels to black
        for (int px = 0; px < width; px++) {
            for (int py = 0; py < height; py++) {
                pixelWriter.setColor(px, py, Color.BLACK);
            }
        }

        for (int i = 0; i < maxIterations; i++) {
            double newX, newY;
            int r = random.nextInt(100);
            if (r < 1) {
                newX = 0;
                newY = 0.16 * y;
            } else if (r < 86) {
                newX = 0.85 * x + 0.04 * y;
                newY = -0.04 * x + 0.85 * y + 1.6;
            } else if (r < 93) {
                newX = 0.2 * x - 0.26 * y;
                newY = 0.23 * x + 0.22 * y + 1.6;
            } else {
                newX = -0.15 * x + 0.28 * y;
                newY = 0.26 * x + 0.24 * y + 0.44;
            }

            int plotX = (int) (newX * scaleX + offsetX);
            int plotY = (int) (newY * scaleY + offsetY);

            if (plotX >= 0 && plotX < width && plotY >= 0 && plotY < height) {
                pixelWriter.setColor(plotX, plotY, Color.GREEN);
            }

            x = newX;
            y = newY;
        }
    }
}
