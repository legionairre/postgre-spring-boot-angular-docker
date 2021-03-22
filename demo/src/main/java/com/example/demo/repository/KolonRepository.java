package com.example.demo.repository;

import com.example.demo.model.Kolon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface KolonRepository extends JpaRepository<Kolon, Long> {
    public List<Object> findAllByTableId(Long tableId);

}
