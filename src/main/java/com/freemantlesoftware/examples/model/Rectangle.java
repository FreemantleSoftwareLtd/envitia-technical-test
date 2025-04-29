package com.freemantlesoftware.examples.model;

/**
 * Simple representation of a Rectangle.
 * <p>
 * Note that an 'empty rectangle' is a rectangle that cannot contain anything.
 */
public record Rectangle(double x, double y, double width, double height) {
    private static final Rectangle EMPTY_RECTANGLE = new Rectangle(0, 0, 0, 0);

    public double maxX() {
        return x + width;
    }

    public double maxY() {
        return y + height;
    }

    public static Rectangle emptyRectangle() {
        return EMPTY_RECTANGLE;
    }

}
