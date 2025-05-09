DECISIONS
---------
- D01:   This system will not process 'fuzzy' rectangles (assume coordinates for rectangle vertices are on a plane).
- D02:   Use a custom Point or Coordinate object as Java Point2D.Double is a static class as does not allow an array to be
         passed.
- D03:   Use a custom Rectangle class, as Java’s Rectangle2D.Double does not behave as needed (top and left boundaries are included in checks),
         and existing points cannot be easily verified. We initially tested with Rectangle and Rectangle2D.Double, but they do not quite provide 
         the required functionality and are not easily extendable (we could adapt/wrap them, but this might lead to problems later).
- D04:   Validation kept simple to ensure that attributes are valid values.
- D05    Added isNotFinite check — even though this is a negative test, it makes the validators cleaner and more readable.
- D06:   For Rectangles the order of the dots is not important as we only need to identify whether the dots form a valid rectangle.

ASSUMPTIONS
-----------
- Any Java JDK version 8+ is suitable, this shouldn't require 'newer' Java features to work.
- The 'number' defined in the task is the 64-bit IEEE 754 standard as used by Javascript and should be stored as Java
  doubles.
- For this application Double accuracy can be considered 'safe', otherwise a more accurate rectangle utilizing Decimal
  or BigDecimal will be needed.
- Assume that data has been pre-processed and coordinates for rectangle vertices are on a plane.

NOTES
-----
- Coordinates are combined, form a coordinate shape (a bit like a dot-to-dot drawing) - this means that the order of the
  coordinates may be important and this may draw a polygon that is not a rectangle.
- This is for Rectangles but as method defines 'shape', consider possibility of other shapes.
- Points are deemed to be inside the shape if they're fully enclosed by all of the edges. Points which are along the
  edge of the shape are not considered to be inside.
- The coordinates can be floating point numbers.
- Check that the given 'size' (width and height) of the rectangle is as expected. - Yes, checked by ShapeFinderTest
- Rectangle2D.Double generally works however, it has a couple of issues:
    1. The initial position needs to be set otherwise 0,0 is set as a coordinate.
    2. Top and Left Edges will return true, but Bottom and Right edges will return false.
    3. Is a static class.
- Rectangle2D.Float is the same as Rectangle2D.Double with a few casts (which can result in slightly the wrong value due
  to rounding; Double -> Float cast can cause rounding problems.

