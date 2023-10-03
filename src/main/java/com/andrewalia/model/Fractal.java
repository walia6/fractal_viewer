package com.andrewalia.model;

import javafx.scene.image.PixelWriter;

/**
 * Fractal interface defines the contract for any class that wants to implement a fractal generator.
 */
public interface Fractal {

    /**
     * Generates a fractal image and writes the pixels using a PixelWriter.
     *
     * @param pixelWriter    The PixelWriter used to write pixels into an image.
     * @param width          The width of the area where the fractal will be generated.
     * @param height         The height of the area where the fractal will be generated.
     * @param viewportX      The x-coordinate of the viewport.
     * @param viewportY      The y-coordinate of the viewport.
     * @param viewportHeight The height of the viewport.
     * @param iterations     The number of iterations to use in generating the fractal.
     */
    void generateFractal(PixelWriter pixelWriter, int width, int height, double viewportX, double viewportY, double viewportHeight, int iterations);
}
