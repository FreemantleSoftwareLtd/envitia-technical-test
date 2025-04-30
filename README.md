Envitia Coding Task
-------------------
Note that the 'core' service in this project is 'BoundaryDetectionService.java' (service.com.freemantlesoftware.envitia.rectanglepoints.BoundaryDetectionService)
and its working is demonstrated via the JUnit Test 'PointInRectangleTest.java' (service.com.freemantlesoftware.envitia.rectanglepoints.PointInRectangleTest).

Note that this solution is deliberately split into components to help demonstrate how it could be made more adaptable.


Point in Rectangle
------------------

Problem
-------
In our application, we need to know if a given point is contained within a rectangular shape.

Requirements
------------
Create a function called
isInsideRectangle(shape: [number, number][], point: [number, number]): boolean

Input
-----
shape - an array of ordered coordinates pairs of the form [x, y] which, when combined, form a coordinate shape (a bit
like a dot-to-dot
drawing).
point - a coordinate pair of the form [x, y]. The provided point will either be inside the shape, outside the shape or
touching the edge of the
shape.

Other Considerations
--------------------
Points are deemed to be inside the shape if they're fully enclosed by all of the edges. Points which are along the edge
of the shape are not
considered to be inside.

If the shape is not a rectangle (or square), isInsideRectangle should return false, even if the point is inside the
shape.
Points in the shape array may not always represent the corner of a shape. Rectangles can therefore be represented using
more than 4
points.

You can create as many functions as you like to solve the problem.

The use of newer features would be more compelling.

The examples and test data that we have given are ints but the coordinates can be floating point numbers as well in the
real world.

Example
-------
Calling isInsideRectangle with the following input:
shape: [[1,1], [1,7], [10,7], [10,1]]
point: [2,6]
Should return true - as the point falls within the box.
