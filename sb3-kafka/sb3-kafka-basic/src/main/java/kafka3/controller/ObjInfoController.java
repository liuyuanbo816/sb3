package kafka3.controller;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import kafka3.common.ImportExcelUtil;
import kafka3.common.R;
import kafka3.model.ObjInfo;
import kafka3.service.ObjInfoService;
import kafka3.service.PoiService;
import kafka3.vo.ImportResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/objInfo")
@Slf4j
public class ObjInfoController {

    ObjInfoService objInfoServiceImpl;
    PoiService poiServiceImpl;

    @Autowired
    public void setObjInfoServiceImpl(ObjInfoService objInfoServiceImpl) {
        this.objInfoServiceImpl = objInfoServiceImpl;
    }

    @Autowired
    public void setPoiServiceImpl(PoiService poiServiceImpl) {
        this.poiServiceImpl = poiServiceImpl;
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

    @RequestMapping(value = "/exportExcel", method = {RequestMethod.GET, RequestMethod.POST})
    public void exportExcel(@RequestBody Map<String, List<Long>> objInfoMap, HttpServletResponse response) {
        List<Long> objInfoIds = objInfoMap.get("objInfoIds");
        byte[] bytes = poiServiceImpl.exportExcel(objInfoIds);
        response.setContentType("text/html;charset=UTF-8");
        try {
            String fileName = URLEncoder.encode(System.currentTimeMillis() + ".xlsx", "UTF-8");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
            try (ServletOutputStream out = response.getOutputStream()) {
                out.write(bytes);
                out.flush();
            } catch (IOException e) {
                log.error("写出响应流出错", e);
            }
        } catch (UnsupportedEncodingException e) {
            log.error("不支持的编码异常", e);
        }
    }

    @GetMapping("/selectNow")
    public R<?> selectNow() {
        return R.ok(objInfoServiceImpl.selectNow());
    }

    @GetMapping("/objInfoList")
    public R<List<ObjInfo>> objInfoList() {
        return R.ok(objInfoServiceImpl.objInfoList());
    }

    @GetMapping("/selectBatchIds")
    public R<List<ObjInfo>> selectBatchIds(@RequestBody Map<String, List<Long>> objInfoMap) {
        List<Long> objInfoIds = objInfoMap.get("objInfoIds");
        return R.ok(objInfoServiceImpl.selectBatchIds(objInfoIds));
    }
}
