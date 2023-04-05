package org.zmz.sb3.security.examples.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.zmz.sb3.security.examples.vo.response.FileInfoResponse;

import java.io.*;

@RestController
@RequestMapping("/file")
public class FileController {
    public static final Logger LOG = LoggerFactory.getLogger(FileController.class);

    @Value("${file.tmp-path}")
    private String tmpPath;

    @PostMapping("/upload")
    public FileInfoResponse fileUpload(MultipartFile file) throws IOException {
        String name = file.getName();
        String originalFilename = file.getOriginalFilename();
        long size = file.getSize();
        LOG.info("文件名: {}", name);
        LOG.info("原始文件名: {}", originalFilename);
        LOG.info("文件大小: {}", size);

        File localFile = new File(tmpPath, System.currentTimeMillis() + ".txt");
        file.transferTo(localFile);
        return new FileInfoResponse(localFile.getAbsolutePath());
    }

    @GetMapping("/download/{fileId}")
    public void fileDownload(@PathVariable("fileId") String fileId,
                             HttpServletResponse httpServletResponse) {
        try (
                InputStream in = new FileInputStream(new File(tmpPath, fileId + ".txt"));
                OutputStream out = httpServletResponse.getOutputStream()) {
            httpServletResponse.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            httpServletResponse.addHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;file=test.txt");
            FileCopyUtils.copy(in, out);
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
