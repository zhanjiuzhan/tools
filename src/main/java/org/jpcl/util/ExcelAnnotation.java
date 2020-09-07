package org.jpcl.util;

import java.lang.annotation.*;

/**
 * @author Administrator
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value=ElementType.FIELD)
public @interface ExcelAnnotation {
    int columnIndex() default 0;
    String columnName() default "";
}
