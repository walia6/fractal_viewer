package com.andrewalia.controller;

import com.andrewalia.view.GUI;
import com.andrewalia.model.Fractal;

/**
 * FractalController class serves as the controller in the MVC pattern,
 * linking the GUI and Fractal model classes.
 */
public class FractalController {

    // Reference to the GUI view
    private GUI view;

    // The current Fractal object that needs to be rendered
    private Fractal currentFractal;

    /**
     * Constructor initializes the FractalController with a GUI view.
     *
     * @param view The GUI view associated with this controller.
     */
    public FractalController(GUI view) {
        this.view = view;
    }

    /**
     * Sets the Fractal object that needs to be rendered.
     *
     * @param fractal The Fractal object to set.
     */
    public void setFractal(Fractal fractal) {
        this.currentFractal = fractal;
    }

    /**
     * Re-renders the fractal with new parameters.
     * Calls the renderFractal method from the GUI view if a Fractal object has been set.
     *
     * @param viewportX      The x-coordinate of the viewport.
     * @param viewportY      The y-coordinate of the viewport.
     * @param viewportHeight The height of the viewport.
     * @param iterations     The number of iterations for generating the fractal.
     */
    public void reRenderWithParams(double viewportX, double viewportY, double viewportHeight, int iterations) {
        if (currentFractal != null) {
            view.renderFractal(currentFractal, viewportX, viewportY, viewportHeight, iterations);
        }
    }
}
