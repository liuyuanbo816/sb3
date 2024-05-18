package kafka3.controller;

import kafka3.common.ImportExcelUtil;
import kafka3.common.R;
import kafka3.service.ObjInfoService;
import kafka3.service.PoiService;
import kafka3.vo.ImportResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.Iterator;

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
    public R<?> importExcel(MultipartHttpServletRequest request) {
        Iterator<String> fileNames = request.getFileNames();
        String uploadFileName = fileNames.next();
        MultipartFile multipartFile = request.getFile(uploadFileName);
        if (ImportExcelUtil.isExcel(uploadFileName)) {
            ImportResultVo vo = poiServiceImpl.importExcel(multipartFile);
            return R.ok(vo);
        } else {
            return R.fail("请导入Excel格式文件");
        }
    }

    @RequestMapping(value = "/exportExcel")
    public ResponseEntity<byte[]> exportExcel() {
        String fileName = System.currentTimeMillis() + ".xlsx";
        HttpHeaders headers = new HttpHeaders();
        //通知浏览器以attachment（下载方式）打开
        headers.setContentDispositionFormData("attachment", fileName);
        //applicatin/octet-stream: 二进制流数据（最常见的文件下载）
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        byte[] byteArr = poiServiceImpl.exportExcel(fileName);
        return new ResponseEntity<>(byteArr, headers, HttpStatus.CREATED);
    }
}
