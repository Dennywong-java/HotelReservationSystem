package com.hrs.hotelreservationsystem.dao;

import com.hrs.hotelreservationsystem.pojo.Queue;
import com.hrs.hotelreservationsystem.pojo.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface QueueRepository extends JpaRepository<Queue, Integer> {

    //findByQueueId
    @Query(value = "SELECT q FROM Queue q WHERE q.id = :queueId")
    Queue findByQueueId(Integer queueId);

    //findByTableId
    @Query(value = "SELECT q FROM Queue q WHERE q.tableId = :tableId")
    Queue findByTableId(Integer tableId);

    //findNumsOfQueuesByEmailAndTableId
    @Query(value = "SELECT COUNT(q.userId) FROM Queue q WHERE q.userId = :UserId AND q.tableId = :tableId")
    Integer findNumsOfQueuesByUserIdAndTableId(Integer UserId, Integer tableId);

    //findAllByTableIdIn
    @Query(value = "SELECT q FROM Queue q WHERE q.tableId IN (?1)")
    Set<Queue> findAllByTableIdIn(List<Integer> tableIds);
}
