package com.example.demo.model;

import javax.persistence.*;
import javax.persistence.Table;

@Entity
@Table(name = "tablo")
public class Tablo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "tableName")
    private String tableName;

    public Tablo(String tableName) {
        this.tableName = tableName;
    }

    public Tablo() {

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

}
