package com.freemantlesoftware.envitia.task.model.validation;

/**
 * Validates a Rectangle object.
 */
public class RectangleValidator {

    /**
     * Basic validator to ensure that rectangle has valid attributes.
     * Does not detect slope ratio or position of vertices.
     *
     * @param x top left X coordinate of the rectangle.
     * @param y top left Y coordinate of the rectangle.
     * @param width width of the rectangle.
     * @param height height of the rectangle.
     * @throws IllegalArgumentException
     */
    public void validate(double x, double y, double width, double height) throws IllegalArgumentException {

        if (isNotFinite(x) || isNotFinite(y) || isNotFinite(width) || isNotFinite(height)) {
            throw new IllegalArgumentException("Rectangle requires argument values to be finite.");
        }

        if (width == 0 || height == 0) {
            throw new IllegalArgumentException("Rectangle width and height values cannot be zero.");
        }
    }

    private boolean isNotFinite(double value) {
        return !Double.isFinite(value);
    }

}
