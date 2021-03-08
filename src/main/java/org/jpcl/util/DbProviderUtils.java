package org.jpcl.util;

/**
 * @author Administrator
 */
public class DbProviderUtils {

    protected String getLimit(int currentPage, int pageSize) {
        return " limit "
                + (currentPage - 1) * pageSize
                + ", " + pageSize;
    }

    protected String getDataVal(String field) {
        return "DATE_FORMAT(" + field +  ", '%Y-%m-%d')";
    }

    protected String getSVal(final String stringFiled) {
        return "'" + stringFiled + "'";
    }
}
