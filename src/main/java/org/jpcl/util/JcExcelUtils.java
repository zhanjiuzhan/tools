package org.jpcl.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

/**
 * @author Administrator
 */
public class JcExcelUtils {
    /**
     * 读取Excel的文件内容 用来转换成对象的
     * @param inputStream
     * @param fileName
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T extends Object> List<T> readExcelObject(InputStream inputStream, String fileName, Class<T> clazz) {
        List<T> list = new ArrayList<>();
        Workbook workbook = null;

        if (fileName.endsWith(".xls")) {
            workbook = xls(inputStream);
        } else if (fileName.endsWith("xlsx")) {
            workbook = xlsx(inputStream);
        } else {
            throw new RuntimeException("非Excel文件");
        }
        // 取得第一个sheet页
        Sheet sheet = workbook.getSheetAt(0);

        // 取得总行数
        int rows = sheet.getLastRowNum() + 1;
        Field[] fields = clazz.getDeclaredFields();
        for (int i = 1; i < 4794; i++) {
            Row row = sheet.getRow(i);
            //此处用来过滤空行
            Cell cell0 = row.getCell( 0);
            cell0.setCellType(CellType.STRING);
            Cell cell1 = row.getCell( 1);
            cell1.setCellType(CellType.STRING);
            if (cell0.getStringCellValue() == "" && cell1.getStringCellValue() == "") {
                continue;
            }
            try {
                T obj = clazz.newInstance();
                for (Field f : fields) {
                    f.setAccessible(true);
                    if (f.isAnnotationPresent(ExcelAnnotation.class)) {
                        ExcelAnnotation excelAnnotation = f.getAnnotation(ExcelAnnotation.class);
                        int columnIndex = excelAnnotation.columnIndex();
                        Cell cell = row.getCell(columnIndex);
                        setFieldValue(obj, f, cell);
                    }
                }
                list.add(obj);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("excel文件内容出错");
            }
        }
        try {
            workbook.close();
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 做成Excel信息
     * @param exportPath
     * @param templateModel
     * @param data
     * @param function
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T> XSSFWorkbook exportExcel(final String exportPath, Class<T> templateModel, List<T> data,
                                               BiConsumer<XSSFRow, T> function) {
        // 选用Excel模板
        XSSFWorkbook wb = null;
        XSSFSheet sheet = null;
        try {
            InputStream in = new FileInputStream(new File(exportPath));
            wb = new XSSFWorkbook(in);
            sheet = wb.getSheetAt(0);
            sheet.setForceFormulaRecalculation(true);
        } catch (Exception e) {
            e.printStackTrace();
        }

        XSSFRow row;
        // 设置表头部
        Field[] fields = templateModel.getDeclaredFields();
        row = getRow(sheet, 0);
        for (Field f : fields) {
            f.setAccessible(true);
            if (f.isAnnotationPresent(ExcelAnnotation.class)) {
                ExcelAnnotation excelAnnotation = f.getAnnotation(ExcelAnnotation.class);
                int columnIndex = excelAnnotation.columnIndex();
                String columnName = excelAnnotation.columnName();
                getCell(row, columnIndex).setCellValue(columnName);
            }
        }

        // 设置表中内容
        int rowIndex = 1;
        for (T model : data) {
            row = getRow(sheet, rowIndex);
            function.accept(row, model);
            rowIndex++;
        }

        return wb;
    }


    private static void setFieldValue(Object obj, Field f, Cell cell) {
        try {
            if (f.getType() == byte.class || f.getType() == Byte.class) {
                f.set(obj, Byte.parseByte(cell.getStringCellValue()));
            } else if (f.getType() == int.class || f.getType() == Integer.class) {
                f.set(obj, Integer.parseInt(cell.getStringCellValue()));
            } else if (f.getType() == Double.class || f.getType() == double.class) {
                f.set(obj, Double.parseDouble(cell.getStringCellValue()));
            } else if (f.getType() == BigDecimal.class) {
                f.set(obj, new BigDecimal(cell.getStringCellValue()));
            } else if (f.getType() == long.class) {
                f.set(obj, new Long(cell.getStringCellValue()));
            } else {
                String str = importByExcelForDate(cell);
                f.set(obj, str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String importByExcelForDate(Cell currentCell) {
        String currentCellValue = "";
        // 判断单元格数据是否是日期
        if ("yyyy-mm-dd;@".equals(currentCell.getCellStyle().getDataFormatString())
                || "m/d/yy".equals(currentCell.getCellStyle().getDataFormatString())
                || "yy/m/d".equals(currentCell.getCellStyle().getDataFormatString())
                || "mm/dd/yy".equals(currentCell.getCellStyle().getDataFormatString())
                || "dd-mmm-yy".equals(currentCell.getCellStyle().getDataFormatString())
                || "yyyy/m/d".equals(currentCell.getCellStyle().getDataFormatString())) {
            if (DateUtil.isCellDateFormatted(currentCell)) {
                // 用于转化为日期格式
                Date d = currentCell.getDateCellValue();
                DateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
                currentCellValue = formater.format(d);
            }
        } else {
            // 不是日期原值返回
            currentCellValue = currentCell.toString();
        }
        return currentCellValue;
    }

    /**
     * 对excel 2003处理
     */
    private static Workbook xls(InputStream is) {
        try {
            return new HSSFWorkbook(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 对excel 2007处理
     */
    private static Workbook xlsx(InputStream is) {
        try {
            return new XSSFWorkbook(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static XSSFRow getRow(XSSFSheet sheet, int rowIndex) {
        XSSFRow row = sheet.getRow(rowIndex);
        if (row == null) {
            row = sheet.createRow(rowIndex);
        }
        return row;
    }

    public static XSSFCell getCell(XSSFRow row, int cellIndex) {
        XSSFCell cell = row.getCell(cellIndex);
        if (cell == null) {
            cell = row.createCell(cellIndex);
        }
        return cell;
    }
}
