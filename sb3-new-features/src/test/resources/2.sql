insert into zmgr_logic_columns (COLUMN_ID, LOGIC_TABLE_ID, DATA_COLUMN_ID,
                                COLUMN_CODE, COLUMN_NAME, COLUMN_TYPE,
                                COLUMN_CLASS, COLUMN_DESC, UUID,
                                COLUMN_SORT)
values (#{columnId,jdbcType=BIGINT}, #{logicTableId,jdbcType=BIGINT}, #{dataColumnId,jdbcType=INTEGER},
           #{columnCode,jdbcType=VARCHAR}, #{columnName,jdbcType=VARCHAR}, #{columnType,jdbcType=VARCHAR},
           #{columnClass,jdbcType=VARCHAR}, #{columnDesc,jdbcType=VARCHAR}, #{uuid,jdbcType=VARCHAR},
           #{columnSort,jdbcType=INTEGER})
           <foreach collection= "columnsList" index = "index" item= "item" separator = ",">
       (
           #{item.paramId,jdbcType=BIGINT}, #{item.logicTableId,jdbcType=BIGINT},
           #{item.paramCode,jdbcType=VARCHAR}, #{item.paramValue,jdbcType=VARCHAR}, #{item.paramDesc,jdbcType=VARCHAR}
       )
           </ foreach>