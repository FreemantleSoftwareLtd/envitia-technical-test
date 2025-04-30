package com.freemantlesoftware.envitia.rectanglepoints.model;

import java.util.Arrays;
import java.util.Objects;

/**
 * Custom Point2D class backed by double[] Array.
 */
public class Point2D implements Comparable<Point2D> {

    private final double[] coordinates;

    public Point2D(double[] coordinates) {
        Objects.requireNonNull(coordinates, "Point2D requires a non-null array of coordinates.");
        this.coordinates = coordinates.clone();
    }

    public Point2D(double x, double y) {
        this(new double[]{x, y});
    }

    public double getX() {
        return this.coordinates[0];
    }

    public double getY() {
        return this.coordinates[1];
    }


    @Override
    public int compareTo(Point2D that) {
        int xComparison = 0;
        int yComparison = 0;

        if (that == null) {
            return 1; // Consider this object greater than null.
        }

        if (this.equals(that)) {
            return 0;
        }

        if (this.getX() > that.getX()) {
            xComparison = 1;
        } else if (this.getX() < that.getX()) {
            xComparison = -1;
        }

        if (this.getY() > that.getY()) {
            yComparison = 1;
        } else if (this.getY() < that.getY()) {
            yComparison = -1;
        }

        return xComparison + yComparison;
    }

    @Override
    public boolean equals(Object that) {
        if (that == null || this.getClass() != that.getClass()) {
            return false;
        }
        Point2D point = (Point2D) that;
        return Objects.deepEquals(this.coordinates, point.coordinates);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(this.coordinates);
    }
}
