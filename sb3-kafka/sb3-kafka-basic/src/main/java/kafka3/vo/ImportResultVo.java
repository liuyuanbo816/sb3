package kafka3.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class ImportResultVo implements Serializable {
    private Integer importTotal = 0;
    private Integer importSuccess = 0;
    private Integer importFail = 0;
    /**
     * 导入信息
     */
    private String importMsg;
    private List<String> importErrorList;
}