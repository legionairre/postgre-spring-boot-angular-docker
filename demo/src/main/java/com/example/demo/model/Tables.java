package com.example.demo.model;

import javax.persistence.*;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;


public class Tables {


    private long id;

    private String tableName;

    private ArrayList columns;

    public Tables(String tableName, ArrayList columns) {
        this.tableName = tableName;
        this.columns = columns;
    }

    public Tables() {

    }

    public Tables(String tableName) {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public ArrayList getColumns() {
        return columns;
    }

    public void setColumns(ArrayList columns) {
        this.columns = columns;
    }
}
