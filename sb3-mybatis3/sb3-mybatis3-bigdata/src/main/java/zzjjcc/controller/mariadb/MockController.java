package zzjjcc.controller.mariadb;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Map;

@RestController
public class MockController {

    public static final Logger LOG = LoggerFactory.getLogger(MockController.class);

    ObjectMapper objectMapper;

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

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
            LOG.error("发生异常", e);
        } finally {
            if (file != null) {
                file.delete();
            }
        }
    }

    @PostMapping("/mock/upload")
    public String readFileContent(HttpServletRequest request) {
        Path path = null;
        try {
            if (request instanceof StandardMultipartHttpServletRequest multipartHttpServletRequest) {
                List<MultipartFile> files = multipartHttpServletRequest.getFiles("file");
                if (!files.isEmpty()) {
                    MultipartFile firstFile = files.getFirst();
                    String fileName = firstFile.getOriginalFilename();

                    path = Files.write(
                            Paths.get("D:\\hjWork\\tmp\\" + fileName),
                            firstFile.getBytes(),
                            StandardOpenOption.CREATE_NEW);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (path != null) {
                boolean bool = path.toFile().delete();
                if (bool) {
                    LOG.info("文件清理成功: {}", path.toAbsolutePath());
                }
            }
        }
        return "OK";
    }

    @GetMapping("/t0516")
    public void t0516(HttpServletResponse response) {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        try (PrintWriter writer = response.getWriter()) {
            Map<String, ? extends Serializable> map = Map.of(
                    "storePath", "d:/hj-work/tmp/0515.log",
                    "objKey", "111",
                    "realName", "日志log",
                    "accId", 0,
                    "curPos", 0
            );
            String json = objectMapper.writeValueAsString(map);
            writer.println(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
