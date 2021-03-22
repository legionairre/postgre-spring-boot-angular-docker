package com.example.demo.model;

import java.util.List;

public class Response {
    long id;
    String tableName;
    List<Object> columns;

    public String getTableName() {
        return tableName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<Object> getColumns() {
        return columns;
    }

    public void setColumns(List<Object> columns) {
        this.columns = columns;
    }
}
