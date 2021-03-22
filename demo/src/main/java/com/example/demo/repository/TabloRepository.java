package com.example.demo.repository;

import com.example.demo.model.Tablo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TabloRepository extends JpaRepository<Tablo, Long> {

    List<Tablo> findByTableName(String tableName);

}
