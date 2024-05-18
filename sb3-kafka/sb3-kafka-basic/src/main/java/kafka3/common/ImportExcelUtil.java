package kafka3.common;

import org.apache.poi.ss.usermodel.*;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ImportExcelUtil {
    private ImportExcelUtil() {
    }

    /**
     * 读取Excel数据
     *
     * @param fieldRow     - 字段所在行，
     * @param dataStartRow - 数据开始行
     * @param dateFormat   - 日期类型处理的格式
     * @return 数据集合
     */
    public static List<String[]> getExcelData(Workbook workbook,
                                              int fieldRow,
                                              int dataStartRow, String dateFormat) {
        List<String[]> dataList = new ArrayList<>();
        Sheet sheet = workbook.getSheetAt(0);
        // 获取总行数(不包含合计行)
        int rowNum = sheet.getLastRowNum();
        // 根据字段的行获取总列数
        int colNum = sheet.getRow(fieldRow).getPhysicalNumberOfCells();
        // 从数据开始行读取excel数据
        for (int i = dataStartRow; i <= rowNum; i++) {
            String[] rowArr = new String[colNum];
            Row row = sheet.getRow(i);
            for (int j = 0; j < colNum; j++) {
                Cell cell = row.getCell(j);
                rowArr[j] = getCellValue(cell, dateFormat);
            }
            dataList.add(rowArr);
        }
        return dataList;
    }

    /**
     * 获取单元格的值，返回String
     *
     * @param dateFormat 日期的格式
     * @return 返回String
     */
    public static String getCellValue(Cell cell, String dateFormat) {
        String cellValue = null;
        if (cell == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(
                (dateFormat == null || dateFormat.trim().isEmpty() ? "yyyy-MM-dd" : dateFormat));
        DecimalFormat df = new DecimalFormat("#0.####################");
        switch (cell.getCellType()) {
            case STRING: // 字符串
                cellValue = cell.getStringCellValue();
                break;
            case NUMERIC: // 数值，货币，日期，时间等都是这种格式，在此要进行判断
                if (DateUtil.isCellDateFormatted(cell)) {
                    cellValue = sdf.format(cell.getDateCellValue());
                } else {
                    cellValue = df.format(cell.getNumericCellValue());
                }
                break;
            case BOOLEAN: // Boolean
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case FORMULA: // 公式
                cellValue = String.valueOf(cell.getCellFormula());
                break;
            case BLANK: // 空值
                break;
            default:
                cellValue = "";
                break;
        }
        return cellValue;
    }

    public static String getCellValue(Cell cell) {
        return getCellValue(cell, null);
    }


    /**
     * 是否是excel
     *
     * @param filePath 文件名或者文件路径
     * @return true-是, false-否
     */
    public static boolean isExcel(String filePath) {
        return filePath.matches("^.+\\.(?i)(xlsx|xls)$");
    }

    /**
     * 是否是2003的excel
     *
     * @param filePath - 文件名或者文件路径
     * @return true-是, false-否
     */
    public static boolean isExcel2003(String filePath) {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    /**
     * 是否是2007的excel
     *
     * @param filePath -文件名或者文件路径
     * @return true-是, false-否
     */
    public static boolean isExcel2007(String filePath) {
        // 以任意个字符开头，用.分割，最后的xlsx不区分大小写。
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }

}