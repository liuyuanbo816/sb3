package org.zjc.smalltools.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.IOException;

@RestController
public class FileController {

    public static final Logger LOG = LoggerFactory.getLogger(FileController.class);

    @GetMapping("/file2")
    public void file2(@RequestParam String filePath) {
        try (FileInputStream fis = new FileInputStream(filePath)) {
            // 远程调用接口

        } catch (IOException e) {
            LOG.info("文件路径不存在: {}", filePath);
        }

    }
}
