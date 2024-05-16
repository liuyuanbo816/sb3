package kafka3.service;

import java.io.File;

public interface PoiService {

    String importExcel(File file);

    byte[] exportExcel(String fileName);
}
