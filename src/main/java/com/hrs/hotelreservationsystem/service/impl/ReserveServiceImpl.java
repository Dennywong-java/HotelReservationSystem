package com.hrs.hotelreservationsystem.service.impl;


import com.hrs.hotelreservationsystem.dao.QueueRepository;
import com.hrs.hotelreservationsystem.dao.TableRepository;
import com.hrs.hotelreservationsystem.dao.UserRepository;
import com.hrs.hotelreservationsystem.exception.myException;
import com.hrs.hotelreservationsystem.pojo.Queue;
import com.hrs.hotelreservationsystem.pojo.Table;
import com.hrs.hotelreservationsystem.pojo.User;
import com.hrs.hotelreservationsystem.pojo.requestbody.ReserveRequest;
import com.hrs.hotelreservationsystem.service.ReserveService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ReserveServiceImpl implements ReserveService {

    private Logger logger = LoggerFactory.getLogger("Reserve Service Loggers");

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TableRepository tableRepository;
    @Autowired
    private QueueRepository queueRepository;

    @Override
    public String reserveTable(ReserveRequest reserveRequest) {
        if (reserveRequest.getTableId().size() == 0) throw new myException("No table is selected");
        User user = userRepository.findByEmail(reserveRequest.getEmail());
        Set<Table> tableSet = tableRepository.findAllByIdIn(reserveRequest.getTableId());
        if (user == null) {
            throw new myException("User not found!");
        } else if (tableSet == null) {
            throw new myException("Tables not found!");
        } else {
            // check if the table is available
            for (Table t : tableSet) {
                if (t.getIs_available() == 1) {
                    throw new myException("Table " + t.getId() + " is not available!");
                }
            }
        }

        // set all tables to unavailable and save
        for (Table t : tableSet) {
            t.setIs_available((byte) 1);
        }
        tableRepository.saveAll(tableSet);

        // create a queue list and save all tables to it
        Set<Queue> queueSet = new HashSet<>(reserveRequest.getTableId().size());
        for (Table t : tableSet) {
            Queue queue = new Queue();
            Double id = Math.random() * 10000;
            Integer integer = id.intValue();
            queue.setId(integer);
            queue.setTableId(t.getId());
            queue.setUserId(user.getId());
            queue.setPartySize(reserveRequest.getPartySize());
            queue.setReserveTime(new Date(System.currentTimeMillis()));
            queueSet.add(queue);
        }
        queueRepository.saveAll(queueSet);
        return "Tables reserved successfully!";
    }

    @Override
    public String updateReserveTable(ReserveRequest reserveRequest) {
        if (reserveRequest.getTableId().size() == 0) throw new myException("No table is selected");
        User user = userRepository.findByEmail(reserveRequest.getEmail());
        Set<Table> tableSet = tableRepository.findAllByIdIn(reserveRequest.getTableId());
        if (user == null) {
            throw new myException("User not found!");
        } else if (tableSet == null) {
            throw new myException("Tables not found!");
        }
        // check if the table is under the user's name
        else if (!checkTableOwner(user, reserveRequest.getTableId())) {
            throw new myException("You don't have the permission to update these tables!");
        } else {
            Set<Queue> queueSet = queueRepository.findAllByTableIdIn(reserveRequest.getTableId());
            for (Queue q : queueSet) {
                q.setPartySize(reserveRequest.getPartySize());
                q.setReserveTime(new Date(System.currentTimeMillis()));
            }
            queueRepository.saveAll(queueSet);
            return "Table Updated Successfully!";
        }
    }

    private boolean checkTableOwner(User user, List<Integer> tableSetIds) {
        // find all queues by table ids
        Set<Queue> queueSet = queueRepository.findAllByTableIdIn(tableSetIds);
        if(queueSet.size() == 0) return false;
        for (Queue q : queueSet) {
            int QueueUserId = q.getUserId();
            int userId = user.getId();
            if (QueueUserId != userId) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String deleteReserveTable(ReserveRequest reserveRequest) {
        if (reserveRequest.getTableId().size() == 0) throw new myException("No table is selected");
        User user = userRepository.findByEmail(reserveRequest.getEmail());
        Set<Table> tableSet = tableRepository.findAllByIdIn(reserveRequest.getTableId());
        if (user == null) {
            throw new myException("User not found!");
        } else if (tableSet == null) {
            throw new myException("Tables not found!");
        }
        // check if the table is under the user's name
        else if (!checkTableOwner(user, reserveRequest.getTableId())) {
            throw new myException("You don't have the permission to update these tables!");
        } else {

            // set all tables to available and save
            for (Table t : tableSet) {
                t.setIs_available((byte) 0);
            }
            tableRepository.saveAll(tableSet);

            Set<Queue> queueSet = queueRepository.findAllByTableIdIn(reserveRequest.getTableId());
            queueRepository.deleteAll(queueSet);
            return "Table Deleted Successfully!";
        }
    }
}
