package com.freemantlesoftware.envitia.rectanglepoints.model.validation.util;

/**
 * Simple utility class for common validation code.
 */
public class ValidationUtils {

    /**
     * Checks if a given double is not finite.
     * This will check for NaN and Positive and Negative Infinity.
     *
     * @param value value to be checked
     * @return true if the value is not finite, else false.
     */
    public static boolean isNotFinite(double value) {
        return !Double.isFinite(value);
    }

}
