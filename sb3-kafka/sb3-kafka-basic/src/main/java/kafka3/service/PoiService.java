package kafka3.service;

import kafka3.vo.ImportResultVo;
import org.springframework.web.multipart.MultipartFile;

public interface PoiService {

    ImportResultVo importExcel(MultipartFile file);

    byte[] exportExcel(String fileName);
}
