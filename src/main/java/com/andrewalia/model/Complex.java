package com.andrewalia.model;

// Complex.java
public class Complex {
    public double real;
    public double imag;

    public Complex(double real, double imag) {
        this.real = real;
        this.imag = imag;
    }

    public Complex add(Complex b) {
        return new Complex(this.real + b.real, this.imag + b.imag);
    }

    public Complex subtract(Complex b) {
        return new Complex(this.real - b.real, this.imag - b.imag);
    }

    public Complex multiply(Complex b) {
        return new Complex(this.real * b.real - this.imag * b.imag,
                           this.real * b.imag + this.imag * b.real);
    }

    public Complex divide(Complex b) {
        double denominator = b.real * b.real + b.imag * b.imag;
        return new Complex((this.real * b.real + this.imag * b.imag) / denominator,
                           (this.imag * b.real - this.real * b.imag) / denominator);
    }

    public double magnitude() {
        return Math.sqrt(this.real * this.real + this.imag * this.imag);
    }
}
