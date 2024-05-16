package kafka3.service.impl;

import kafka3.common.ExportExcelUtil;
import kafka3.common.ImportExcelUtil;
import kafka3.service.PoiService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PoiServiceImpl implements PoiService {
    @Override
    public String importExcel(File file) {
        // poi_xlsx.xlsx
        String fileName = file.getName();
        List<String[]> dataList = null;
        String headData = null;
        Workbook workbook = null;
        String dateFormat = "yyyy-MM-dd";
        try {
            if (ImportExcelUtil.isExcel2007(fileName)) {
                workbook = new XSSFWorkbook(new FileInputStream(file));
            } else if (ImportExcelUtil.isExcel2003(fileName)) {
                workbook = new HSSFWorkbook(new FileInputStream(file));
            }
            if (workbook != null) {
                headData = ImportExcelUtil.getExcelHeadData(workbook);
                dataList = ImportExcelUtil.getExcelData(workbook, 1, 2, dateFormat);
            }
            //拿到数据进行数据库操作
            System.out.println(headData);
            SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
            List<String[]> list = Optional.ofNullable(dataList).orElse(new ArrayList<>());
            for (String[] strArr : list) {
                Map<String, ?> entityMap = new LinkedHashMap<>();
                for (int i = 0; i < strArr.length; i++) {
                    String cellValue = strArr[i];
                    switch (i) {
                        case 0:
                            entityMap.put("name", cellValue);
                            break;
                        case 1:
                            entityMap.put("sex", cellValue);
                            break;
                        case 2:
                            entityMap.put("age", Integer.valueOf(cellValue));
                            break;
                        case 3:
                            entityMap.put("height", Double.valueOf(cellValue));
                            break;
                        case 4:
                            entityMap.put("birth", sdf.parse(cellValue));
                            break;
                        case 5:
                            entityMap.put("intro", cellValue);
                            break;
                        default:
                            break;
                    }
                }
            }
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (workbook != null) {
                try {
                    workbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "false";
    }

    public byte[] exportExcel(String fileName) {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        List<Map<String, Object>> exportDataList = poiEntityDOMapper.selectByExport();
        String head = "2020-3-20人员信息登记表";
        String[] fieldNameCNArr = new String[]{"姓名", "性别", "年龄", "身高（m）", "出生日期", "创建时间", "简介"};
        String[] fieldNameENArr = new String[]{"name", "sex", "age", "height", "birthday", "createtime", "intro"};

        Workbook workbook = null;
        try {
            if (ImportExcelUtil.isExcel2007(fileName)) {
                workbook = new XSSFWorkbook();
            } else if (ImportExcelUtil.isExcel2003(fileName)) {
                workbook = new HSSFWorkbook();
            }
            //创建一张excel表
            Sheet sheet = workbook.createSheet("sheet1");
            // 初始化列的宽度
            for (int i = 0; i < fieldNameCNArr.length; i++) {
                switch (i) {
                    case 4:
                        ExportExcelUtil.createColumnWidth(sheet, i, 20);
                        break;
                    case 5:
                        ExportExcelUtil.createColumnWidth(sheet, i, 20);
                        break;
                    case 6:
                        ExportExcelUtil.createColumnWidth(sheet, i, 30);
                        break;
                    default:
                        ExportExcelUtil.createColumnWidth(sheet, i, 10);
                        break;
                }
            }

            //创建并初始化表头
            int headStartRow = 0;
            CellStyle style = ExportExcelUtil.createCellStyle(workbook, "宋体", true, (short) 18, Font.COLOR_NORMAL);
            ExportExcelUtil.setCellStyle(style, HorizontalAlignment.CENTER, VerticalAlignment.CENTER, false);
            ExportExcelUtil.creatSheetHead(sheet, style, head, headStartRow, 0, 0, 0, 6);
            //创建并初始化字段行
            int feildStartRow = 1;
            Row fieldRow = sheet.createRow(feildStartRow);
            style = ExportExcelUtil.createCellStyle(workbook, "宋体", true, (short) 14, Font.COLOR_NORMAL);
            ExportExcelUtil.setCellStyle(style, HorizontalAlignment.CENTER, VerticalAlignment.CENTER, true);
            for (int i = 0; i < fieldNameCNArr.length; i++) {
                Cell cell = fieldRow.createCell(i);
                cell.setCellStyle(style);
                cell.setCellValue(fieldNameCNArr[i]);
            }

            //从数据开始行创建并初始化数据单元格
            int dataStartRow = 2;
            style = ExportExcelUtil.createCellStyle(workbook, "宋体", false, (short) 12, Font.COLOR_NORMAL);
            ExportExcelUtil.setCellStyle(style, HorizontalAlignment.CENTER, VerticalAlignment.CENTER, true);
            ExportExcelUtil.fillSheetData(sheet, style, dataStartRow, exportDataList, fieldNameENArr);
            workbook.write(byteOut);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (workbook != null) {
                try {
                    workbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return byteOut.toByteArray();
    }
}
