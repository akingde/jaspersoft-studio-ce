package com.jaspersoft.hbase.deserialize;

/**
 * 
 * @author Eric Diaz
 * 
 */
public interface Deserializer {
    public Object deserializeRowId(byte[] rowID);

    public String deserializeColumnFamily(byte[] columnFamily);

    public String deserializeQualifier(byte[] qualifier);

    public Object deserializeValue(String tableName, String columnFamily, String qualifier, byte[] value);

    public byte[] serializeRowId(String rowID);

    public byte[] serializeColumnFamily(String columnFamily);

    public byte[] serializeQualifier(String qualifier);

    public byte[] serializeValue(String tableName, String columnFamily, String qualifier, Object value);
}