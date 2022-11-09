package com.hrs.hotelreservationsystem.dao;

import com.hrs.hotelreservationsystem.pojo.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface TableRepository extends JpaRepository<Table, Integer> {
    @Query(value = "SELECT t FROM Table t WHERE t.id IN (?1)")
    Set<Table> findAllByIdIn(List<Integer> tableId);
}

