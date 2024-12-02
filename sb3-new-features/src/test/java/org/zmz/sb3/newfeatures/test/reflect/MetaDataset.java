package org.zmz.sb3.newfeatures.test.reflect;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class MetaDataset implements Serializable {
    private Long iId;
    private String sMetaCode;
    private String sMetaVersion;
    private String iTempletId;
    private String sTempletCode;
    private String sTempletSmallType;
    private String sMetaParentCode;
    private String sName;
    private String sEnName;
    private Long iAssetType;
    private String sLayer;
    private String sDomain;
    private String sSubDomain;
    private Date dMetaUpdateTime;
    private String sDesc;
    private Integer iStatus;
    private Long iCreateStaffId;
    private String sCreateStaffName;
    private Date dCreateTime;
    private Long iUpdateStaffId;
    private String sUpdateStaffName;
    private Date dUpdateTime;
    private Integer iModifyStatus;
    private String sCollectId;
    private String sParentCollectId;
    private Long iSourceType;
    private String sSourceMetaCode;
    private String sSystemCode;
    private String sDemander;
    private String sManager;
    private String sDeveloper;
    private String sPublisher;
    private String sOperator;
    private Long iAccComId;
    private Date dMetaCreateTime;
    private String sExtendField;
    private String sExtendString1;
    private String sExtendString2;
    private String sExtendString3;
    private String sExtendString4;
    private String sExtendString5;
    private String sExtendString6;
    private String sExtendString7;
    private String sExtendString8;
    private String sExtendString9;
    private Long iExtendNumber1;
    private Double iExtendNumber2;
    private Double iExtendNumber3;
    private Double iExtendNumber4;
    private Double iExtendNumber5;
    private Double iExtendNumber6;
    private Double iExtendNumber7;
    private Double iExtendNumber8;
    private Double iExtendNumber9;
    private Date dExtendDate1;
    private Date dExtendDate2;
    private Date dExtendDate3;
    private Date dExtendDate4;
    private Date dExtendDate5;
    private String metaFrom;
    private String gradingFilingSystemName;
    private String professionalCode;
    private Integer iLostField;
    private String sLostFieldDetail;
    private Long dataWarehouseId;
    private String dataWarehouseName;
    private String iSourePkId;
    private Long iAssetTypeLogic;
    private Long iAssetTypeSdefine;
    private List<MetaDataset> children;
    private Integer levelCount;
    private String viewNum;
    private String bookingNum;
    private String iSpendTime;
    private String tableStorageSpace;
    private String iHeatScore;
    //private List<MetaDatasetLabelRel> relList;
}
