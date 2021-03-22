package com.example.demo.model;

import javax.persistence.*;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "kolon")
public class Kolon implements Serializable {

    @Id
    @GeneratedValue
    private Long columnId;
    @Column
    private Long tableId;
    @Column(length = 100)
    private String columnName;

    public Kolon() {
    }

    public Kolon(Long tableId, String columnName) {
        this.tableId = tableId;
        this.columnName = columnName;
    }

    public Long getColumnId() {
        return columnId;
    }

    public void setColumnId(Long columnId) {
        this.columnId = columnId;
    }

    public Long getTableId() {
        return tableId;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }
}
