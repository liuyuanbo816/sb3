package kafka3.service;

import kafka3.vo.ImportResultVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PoiService {

    ImportResultVo importExcel(MultipartFile file);

    byte[] exportExcel(List<Long> objInfoIds);
}
