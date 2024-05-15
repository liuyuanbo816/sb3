package zzjjcc.controller;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

/**
 * http://localhost:9999/sdk/upload/entireUpload
 */
@RestController
public class MockController {

    public static final Logger LOG = LoggerFactory.getLogger(MockController.class);

    @Getter
    @Setter
    static class Resp {
        private String success;
        private String code;
        private String msg;
        /**
         * {
         * "storePath":"文件上传路径",
         * "objKey":"文件ID",
         * "realName":"文件名称",
         * "accId":0,
         * "curPos":0
         * }
         */
        private Map<String, Object> data;
    }

    @PostMapping("/sdk/upload/entireUpload")
    public Resp mockSuccess() {
        Resp resp = new Resp();

        resp.setSuccess("true");
        resp.setCode("0");
        resp.setMsg("ok");
        resp.setData(
                Map.of(
                        "storePath", "d:/hj-work/tmp/0515.log",
                        "objKey", "111",
                        "realName", "日志log",
                        "accId", 0,
                        "curPos", 0
                )
        );
        return resp;
    }

//    public Resp mockFail() {
//        Resp resp = new Resp();
//
//        resp.setSuccess("false");
//        resp.setCode("1");
//        resp.setMsg("fail");
//        resp.setData(null);
//        return resp;
//    }

    @PostMapping("/createTmpFile")
    public void createTmpFile(@RequestBody Map<String, String> params) {
        String filePath = params.get("filePath");
        String fileName = params.get("fileName");
        String fileType = params.get("fileType");

        File file = null;
        try {
            Path path = Files.createTempFile(Paths.get(filePath), "tmp_" + fileName, "." + fileType);
            file = path.toFile();
            LOG.info("{}", file.getAbsolutePath());
            LOG.info("{}", file.getName());
            LOG.info("{}", file.toURI());
            LOG.info("{}", file.length());
            file.deleteOnExit();
        } catch (IOException e) {
            LOG.error("{}", e);
        } finally {
            if (file != null) {
                file.delete();
            }
        }

    }


}
