package kafka3.controller;

import kafka3.common.ImportExcelUtil;
import kafka3.service.ObjInfoService;
import kafka3.service.PoiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/objInfo")
public class ObjInfoController {

    ObjInfoService objInfoServiceImpl;
    PoiService poiServiceImpl;

    @Autowired
    public void setObjInfoServiceImpl(ObjInfoService objInfoServiceImpl) {
        this.objInfoServiceImpl = objInfoServiceImpl;
    }

    @GetMapping("/importExcel")
    public String importExcel(String filePath) {
        File file = new File(filePath);
        if (ImportExcelUtil.isExcel(file.getName())) {
            String result = poiServiceImpl.importExcel(file);
            return "importExcel -- " + result;
        } else {
            return "importExcel -- 文件类型与模板不符！";
        }
    }

    @RequestMapping(value = "/exportXLSX")
    public ResponseEntity<byte[]> exportExcel() {
        String fileName = "导出xlsx数据.xlsx";
        HttpHeaders headers = new HttpHeaders();
        //下载显示的文件名，并解决中文名称乱码问题
        String downloadFileName = new String(
                fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
        //通知浏览器以attachment（下载方式）打开
        headers.setContentDispositionFormData("attachment", downloadFileName);
        //applicatin/octet-stream: 二进制流数据（最常见的文件下载）
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        byte[] byteArr = poiServiceImpl.exportExcel(fileName);
        return new ResponseEntity<>(byteArr, headers, HttpStatus.CREATED);
    }
}
