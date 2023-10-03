package com.andrewalia.model;

import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

/**
 * The Mandelbrot class implements the Fractal interface
 * to generate the Mandelbrot fractal.
 */
public class Mandelbrot implements Fractal {

    /**
     * Generate the Mandelbrot fractal and write it to the PixelWriter.
     * 
     * @param pixelWriter    PixelWriter object to write pixels to an image.
     * @param width          Width of the image.
     * @param height         Height of the image.
     * @param viewportX      X-coordinate of the viewport center.
     * @param viewportY      Y-coordinate of the viewport center.
     * @param viewportHeight Height of the viewport.
     * @param maxIterations  Maximum number of iterations for the fractal.
     */
    @Override
    public void generateFractal(PixelWriter pixelWriter, int width, int height, double viewportX, double viewportY, double viewportHeight, int maxIterations) {
        
        // Calculate viewport width based on height to maintain aspect ratio
        double viewportWidth = viewportHeight * (double) width / height;
        
        // Define the range of x and y coordinates to be plotted
        double minX = viewportX - viewportWidth / 2.0;
        double maxX = viewportX + viewportWidth / 2.0;
        double minY = viewportY - viewportHeight / 2.0;
        double maxY = viewportY + viewportHeight / 2.0;

        // Loop through each pixel in the image
        for (int px = 0; px < width; px++) {
            for (int py = 0; py < height; py++) {
                
                // Map pixel position to corresponding point in the complex plane
                double x0 = minX + (maxX - minX) * px / (width - 1);
                double y0 = minY + (maxY - minY) * py / (height - 1);

                double x = 0.0;
                double y = 0.0;
                int iteration = 0;

                // Mandelbrot iteration
                while (x*x + y*y <= 4 && iteration < maxIterations) {
                    double xTemp = x*x - y*y + x0;
                    y = 2*x*y + y0;
                    x = xTemp;
                    iteration++;
                }

                // Color pixel based on number of iterations
                if (iteration == maxIterations) {
                    pixelWriter.setColor(px, py, Color.BLACK);  // Inside the Mandelbrot set
                } else {
                    // Calculate color using Smooth Coloring Algorithm
                    double zn = Math.sqrt(x*x + y*y);
                    double nu = Math.log(Math.log(zn) / Math.log(2)) / Math.log(2);
                    double n = iteration + 1 - nu;

                    double hue = (360.0 * (n / maxIterations)) % 360.0; // Cyclic coloring
                    double lightness = 70.0 + 20.0 * Math.sin(n / 2.0);
                    double chroma = 100.0;

                    // Convert LCH to RGB (via HSL)
                    Color color = Color.hsb(hue, chroma / 100.0, lightness / 100.0);
                    pixelWriter.setColor(px, py, color);  // Outside the Mandelbrot set
                }
            }
        }
    }
}
