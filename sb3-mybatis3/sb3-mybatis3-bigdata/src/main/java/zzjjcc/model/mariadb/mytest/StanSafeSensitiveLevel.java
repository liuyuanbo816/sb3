package zzjjcc.model.mariadb.mytest;

import java.util.Date;

public class StanSafeSensitiveLevel {

    private Long levelId;


    private String levelCode;


    private String levelName;


    private String levelDesc;


    private String levelColor;


    private Long comAcctId;


    private Long createBy;


    private Date createDate;


    private Long lastModifyBy;


    private Date lastModifyDate;


    private Integer levelSort;


    public Long getLevelId() {
        return levelId;
    }


    public void setLevelId(Long levelId) {
        this.levelId = levelId;
    }


    public String getLevelCode() {
        return levelCode;
    }


    public void setLevelCode(String levelCode) {
        this.levelCode = levelCode;
    }


    public String getLevelName() {
        return levelName;
    }


    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }


    public String getLevelDesc() {
        return levelDesc;
    }


    public void setLevelDesc(String levelDesc) {
        this.levelDesc = levelDesc;
    }


    public String getLevelColor() {
        return levelColor;
    }


    public void setLevelColor(String levelColor) {
        this.levelColor = levelColor;
    }


    public Long getComAcctId() {
        return comAcctId;
    }


    public void setComAcctId(Long comAcctId) {
        this.comAcctId = comAcctId;
    }


    public Long getCreateBy() {
        return createBy;
    }


    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }


    public Date getCreateDate() {
        return createDate;
    }


    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }


    public Long getLastModifyBy() {
        return lastModifyBy;
    }


    public void setLastModifyBy(Long lastModifyBy) {
        this.lastModifyBy = lastModifyBy;
    }


    public Date getLastModifyDate() {
        return lastModifyDate;
    }


    public void setLastModifyDate(Date lastModifyDate) {
        this.lastModifyDate = lastModifyDate;
    }


    public Integer getLevelSort() {
        return levelSort;
    }


    public void setLevelSort(Integer levelSort) {
        this.levelSort = levelSort;
    }
}