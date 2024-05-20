package kafka3.common;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

import java.util.List;
import java.util.Map;


public class ExportExcelUtil {

    private ExportExcelUtil() {
    }

    /**
     * 创建表头
     *
     * @param style        - 表头样式
     * @param head         - 表头内容
     * @param headStartRow - 表头开始行，从0开始
     * @param firstRow     - 合并开始行
     * @param lastRow      - 合并结束行
     * @param firstCol     - 合并开始列
     * @param lastCol      - 合并结束列
     */
    public static void creatSheetHead(Sheet sheet, CellStyle style, String head, int headStartRow, int firstRow, int lastRow, int firstCol, int lastCol) {
        Row row = sheet.createRow(headStartRow);
        Cell cell = row.createCell(headStartRow);
        cell.setCellValue(head);
        cell.setCellStyle(style);
        sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow, firstCol, lastCol));
    }

    /**
     * 创建单元格样式 - 默认左对齐
     *
     * @param name   - 字体名称
     * @param bold   - 是否加粗
     * @param height - 字体大小
     * @param color  - 字体颜色
     */
    public static CellStyle createCellStyle(Workbook workbook, String name, boolean bold, short height, short color) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName(name);
        font.setBold(bold);
        font.setColor(color);
        font.setFontHeightInPoints(height);
        //斜体
        font.setItalic(false);
        style.setFont(font);
        return style;
    }

    /**
     * 设置单元格样式 - 对齐方式和边框
     *
     * @param halign - 水平对齐
     * @param valign - 垂直对齐
     * @param border - true-四边框，false-无边框
     */
    public static void setCellStyle(CellStyle style,
                                    HorizontalAlignment halign,
                                    VerticalAlignment valign, boolean border) {
        style.setAlignment(halign);
        style.setVerticalAlignment(valign);
        if (border) {
            style.setBorderTop(BorderStyle.THIN);
            style.setBorderBottom(BorderStyle.THIN);
            style.setBorderLeft(BorderStyle.THIN);
            style.setBorderRight(BorderStyle.THIN);
        }
    }

    /**
     * 设置列宽
     *
     * @param columnIndex - 从0开始
     * @param width       - 行宽
     */
    public static void createColumnWidth(Sheet sheet, int columnIndex, int width) {
        // 256 * width
        sheet.setColumnWidth(columnIndex, 256 * width + 184);
    }

    /**
     * 填充sheet数据，这里使用map通过字段名取获取值
     *
     * @param rowIndex       - 数据开始行，从0开始
     * @param dataList       - 要填充的数据
     * @param fieldNameENArr - 字段的名称
     */
    public static void fillSheetData(Sheet sheet, CellStyle style,
                                     int rowIndex,
                                     List<Map<String, Object>> dataList,
                                     String[] fieldNameENArr) {
        if (dataList != null && !dataList.isEmpty() && fieldNameENArr.length > 0) {
            for (Map<String, Object> map : dataList) {
                int index = rowIndex++;
                Row row = sheet.createRow(index);
                for (int i = 0; i < fieldNameENArr.length; i++) {
                    Cell cell = row.createCell(i);
                    cell.setCellValue(map.get(fieldNameENArr[i]) != null ? map.get(fieldNameENArr[i]).toString() : "");
                    cell.setCellStyle(style);
                }
            }
        }
    }
}