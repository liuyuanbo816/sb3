package zzjjcc.dto;

public class CommonCodeToNameQueryDTO {
    private String itemName;
    private String tableName;
    private String dbCodeField;
    private String codeValue;

    public String getDbCodeField() {
        return dbCodeField;
    }

    public void setDbCodeField(String dbCodeField) {
        this.dbCodeField = dbCodeField;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getCodeValue() {
        return codeValue;
    }

    public void setCodeValue(String codeValue) {
        this.codeValue = codeValue;
    }
}
