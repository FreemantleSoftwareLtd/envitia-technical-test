package com.freemantlesoftware.envitia.rectanglepoints.service;

import com.freemantlesoftware.envitia.rectanglepoints.model.Point2D;
import com.freemantlesoftware.envitia.rectanglepoints.model.Rectangle;

import java.util.Set;

/**
 * Class that provides basic Boundary Detection methods for a Rectangle.
 */
public class RectangleBoundaryDetection {

    /**
     * Determines if a given point is within the given rectangle.
     * Note that the edges of the rectangle are not considered to be inside the rectangle.
     *
     * @param rectangle
     * @param point
     * @return true is the given point is within the rectangle, else false
     */
    public boolean isPointInsideRectangle(final Rectangle rectangle, final Point2D point) {
        return (rectangle.width() > 0
                && rectangle.height() > 0
                && point.getX() > rectangle.x()
                && point.getY() > rectangle.y()
                && point.getX() < rectangle.maxX()
                && point.getY() < rectangle.maxY()
        );
    }


    /**
     * Finds a point that is within the given rectangle from the set of provided points.
     *
     * @param rectangle
     * @param points
     * @return true if a given point was found within the rectangle, else false.
     */
    public boolean hasPointsInsideRectangle(final Rectangle rectangle, final Set<Point2D> points) {
        return points.stream().anyMatch(p -> this.isPointInsideRectangle(rectangle, p));
    }

}
