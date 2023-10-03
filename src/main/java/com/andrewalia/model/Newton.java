package com.andrewalia.model;

import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

public class Newton implements Fractal {

    private Complex[] roots = {
        new Complex(1, 0),
        new Complex(-0.5, Math.sqrt(3) / 2),
        new Complex(-0.5, -Math.sqrt(3) / 2)
    };
    
    private Color[] rootColors = {
        Color.RED,
        Color.GREEN,
        Color.BLUE
    };

    @Override
    public void generateFractal(PixelWriter pw, int width, int height, 
                                double viewportX, double viewportY, 
                                double viewportHeight, int maxIterations) {

        double dx = viewportHeight / height;
        double dy = viewportHeight / width;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Complex c = new Complex(viewportX + x * dx, viewportY - y * dy);
                int rootIndex = findRoot(c, maxIterations);
                
                if (rootIndex != -1) {
                    pw.setColor(x, y, rootColors[rootIndex]);
                } else {
                    pw.setColor(x, y, Color.BLACK);
                }
            }
        }
    }

    private int findRoot(Complex z, int maxIterations) {
        for (int i = 0; i < maxIterations; i++) {
            Complex f = z.multiply(z).multiply(z).subtract(new Complex(1, 0));
            Complex fPrime = z.multiply(z).multiply(new Complex(3, 0));
            z = z.subtract(f.divide(fPrime));
            
            for (int j = 0; j < roots.length; j++) {
                if (z.subtract(roots[j]).magnitude() < 1e-6) {
                    return j;
                }
            }
        }
        
        return -1;
    }
}
