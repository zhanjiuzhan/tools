package org.jpcl.util;

import java.util.function.BooleanSupplier;

/**
 * @author Administrator
 */
final public class ExceptionUtils {

    public static void checkValid(boolean flag, RuntimeException exception) {
        if (flag) {
            throw exception;
        }
    }

    public static void checkValid(BooleanSupplier function, RuntimeException exception) {
        if (function.getAsBoolean()) {
            throw exception;
        }
    }

    public static void throwException(RuntimeException exception) {
        throw exception;
    }
}
