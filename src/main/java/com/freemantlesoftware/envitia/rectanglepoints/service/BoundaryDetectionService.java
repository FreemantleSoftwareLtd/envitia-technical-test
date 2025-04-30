package com.freemantlesoftware.envitia.rectanglepoints.service;

import com.freemantlesoftware.envitia.rectanglepoints.model.Point2D;
import com.freemantlesoftware.envitia.rectanglepoints.model.Rectangle;
import com.freemantlesoftware.envitia.rectanglepoints.model.validation.Point2DValidator;

import java.util.HashSet;
import java.util.Set;

/**
 * Service that allows shape to be found and boundaries to be checked.
 */
public class BoundaryDetectionService {

    private static final int VERTICES_OF_A_RECTANGLE = 4;

    private final ShapeFinder shapeFinder;
    private final RectangleBoundaryDetection rectangleBoundaryDetection;
    private final Point2DValidator point2DValidator;


    public BoundaryDetectionService(ShapeFinder shapeFinder,
                                    RectangleBoundaryDetection rectangleBoundaryDetection) {
        this.shapeFinder = shapeFinder;
        this.rectangleBoundaryDetection = rectangleBoundaryDetection;
        this.point2DValidator = new Point2DValidator();
    }


    /**
     * Determines if a given coordinate shape is a rectangle and if the given point is within the rectangle.
     * A point is only deemed to be inside the shape if it is fully enclosed by all edges.
     *
     * @param shape - coordinate pairs that define a shape.
     * @param point - coordinate pair that defines a point.
     * @return true if the given shape is a rectangle asn the given point is within the given shape, else false.
     */
    public boolean isInsideRectangle(double[][] shape, double[] point) {

        if (point == null || point.length == 0
                || shape == null || shape.length < VERTICES_OF_A_RECTANGLE) {
            return false; // input data not valid.
        }

        try {

            Set<Point2D> pointsInShape = getShapeAsPoints(shape);

            Rectangle rectangle = shapeFinder.findRectangle(pointsInShape).orElse(Rectangle.emptyRectangle());

            boolean isValidRectangle = Rectangle.emptyRectangle() != rectangle && !rectangleBoundaryDetection.hasPointsInsideRectangle(rectangle, pointsInShape);

            return isValidRectangle && rectangleBoundaryDetection.isPointInsideRectangle(rectangle, new Point2D(point));

        } catch (IllegalArgumentException ex) {
            System.out.printf("An exception occurred while trying to process isInsideRectangle: %s%n", ex); // basic logging.
        }

        return false; // Rectangle Not Found.
    }


    private Set<Point2D> getShapeAsPoints(double[][] shape) {
        Set<Point2D> shapeAsPoints = new HashSet<>();

        for (double[] point : shape) {
            point2DValidator.validate(point);
            shapeAsPoints.add(new Point2D(point));
        }
        return shapeAsPoints;
    }

}
