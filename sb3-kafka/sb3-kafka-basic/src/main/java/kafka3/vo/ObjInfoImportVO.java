package kafka3.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ObjInfoImportVO {
    /**
     * 导入行标识
     */
    private Integer rowNumber;
    /**
     * 维度名称
     */
    private String dimensionsName;
    /**
     * 维度编码
     */
    private String dimensionsCode;
    /**
     * 维度分组
     */
    private String dimensionsGroup;
    /**
     * 维度数据类型
     */
    //private String dimensionsDataType;
    /**
     * 维度来源数据源
     */
    private String dimensionsSourceDataSource;
    /**
     * 维度来源表
     */
    private String dimensionsSourceTable;
    /**
     * 维度来源属性
     */
    private String dimensionsSourceProperty;
    /**
     * 维度描述
     */
    private String dimensionsDesc;
}
