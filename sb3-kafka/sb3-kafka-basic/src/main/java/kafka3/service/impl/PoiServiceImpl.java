package kafka3.service.impl;

import jakarta.annotation.Resource;
import kafka3.common.ExportExcelUtil;
import kafka3.common.ImportExcelUtil;
import kafka3.mapper.dataopen.ObjInfoMapper;
import kafka3.model.ObjInfo;
import kafka3.service.PoiService;
import kafka3.vo.ImportResultVo;
import kafka3.vo.ObjInfoImportVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class PoiServiceImpl implements PoiService {

    @Resource
    ObjInfoMapper objInfoMapper;

    @Override
    public ImportResultVo importExcel(MultipartFile file) {
        List<ObjInfoImportVO> objInfoImportVOS = buildObjInfoVos(file);
        return this.checkAndImport(objInfoImportVOS);
    }

    private ImportResultVo checkAndImport(List<ObjInfoImportVO> objInfoImportVOS) {
        List<ObjInfo> objInfos = new ArrayList<>(objInfoImportVOS.size());
        ImportResultVo vo = new ImportResultVo();

        for (ObjInfoImportVO objInfoImportVo : objInfoImportVOS) {
            ObjInfo objInfo = new ObjInfo();

        }
        return vo;
    }

    private List<ObjInfoImportVO> buildObjInfoVos(MultipartFile file) {
        List<ObjInfoImportVO> objInfoImportVOS = new ArrayList<>();
        try (InputStream in = file.getInputStream();
             Workbook workbook = WorkbookFactory.create(in)) {
            if (workbook != null) {
                Sheet sheet0 = workbook.getSheetAt(0);
                String[] excelLetters = this.getExcelLetters();
                int totalRows = sheet0.getLastRowNum();

                for (int i = 1; i <= totalRows; i++) {
                    Row row = sheet0.getRow(i);
                    ObjInfoImportVO objInfoImportVO = new ObjInfoImportVO();
                    objInfoImportVO.setRowNumber(i);
                    for (int j = 0; j < excelLetters.length; j++) {
                        Cell cell = row.getCell(j);
                        switch (excelLetters[j]) {
                            case "A":
                                objInfoImportVO.setDimensionsName(ImportExcelUtil.getCellValue(cell));
                                break;
                            case "B":
                                objInfoImportVO.setDimensionsCode(ImportExcelUtil.getCellValue(cell));
                                break;
                            case "C":
                                objInfoImportVO.setDimensionsGroup(ImportExcelUtil.getCellValue(cell));
                                break;
                            case "D":
                                objInfoImportVO.setDimensionsDataType(ImportExcelUtil.getCellValue(cell));
                                break;
                            case "E":
                                objInfoImportVO.setDimensionsSourceDataSource(ImportExcelUtil.getCellValue(cell));
                                break;
                            case "F":
                                objInfoImportVO.setDimensionsSourceTable(ImportExcelUtil.getCellValue(cell));
                                break;
                            case "G":
                                objInfoImportVO.setDimensionsSourceProperty(ImportExcelUtil.getCellValue(cell));
                                break;
                            case "H":
                                objInfoImportVO.setDimensionsDesc(ImportExcelUtil.getCellValue(cell));
                                break;
                            default:
                                break;
                        }
                    }
                    objInfoImportVOS.add(objInfoImportVO);
                }
            }
            return objInfoImportVOS;
        } catch (Exception e) {
            log.error("异常01", e);
        }
        return List.of();
    }

    public byte[] exportExcel(String fileName) {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        List<Map<String, Object>> exportDataList = mock();
        String head = "2020-3-20人员信息登记表";
        String[] fieldNameCNArr = new String[]{"姓名", "性别", "年龄", "身高（m）", "出生日期", "创建时间", "简介"};
        String[] fieldNameENArr = new String[]{"name", "sex", "age", "height", "birthday", "createTime", "intro"};

        Workbook workbook = null;
        try {
            if (ImportExcelUtil.isExcel2007(fileName)) {
                workbook = new XSSFWorkbook();
            } else if (ImportExcelUtil.isExcel2003(fileName)) {
                workbook = new HSSFWorkbook();
            }
            Sheet sheet;
            //创建一张excel表
            if (workbook != null) {
                sheet = workbook.createSheet("sheet1");
                // 初始化列的宽度
                for (int i = 0; i < fieldNameCNArr.length; i++) {
                    switch (i) {
                        case 4, 5:
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
            }
        } catch (IOException e) {
            log.error("异常03", e);
        } finally {
            if (workbook != null) {
                try {
                    workbook.close();
                } catch (IOException e) {
                    log.error("异常04", e);
                }
            }
        }
        return byteOut.toByteArray();
    }

    private List<Map<String, Object>> mock() {
        return List.of(
                Map.of()
        );
    }

    /**
     * 获取excel头上的字母,方便查找对应关系
     */
    private String[] getExcelLetters() {
        return new String[]{
                "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
                "V", "W", "X", "Y", "Z", "AA", "AB", "AC", "AD", "AE", "AF", "AG", "AH", "AI", "AJ", "AK", "AL", "AM", "AN",
                "AO", "AP", "AQ"
        };
    }
}
