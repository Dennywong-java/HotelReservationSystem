package com.hrs.hotelreservationsystem.service;

import com.hrs.hotelreservationsystem.pojo.requestbody.ReserveRequest;

public interface ReserveService {

    // reserve table
    String reserveTable(ReserveRequest reserveRequest);

    // update reservation
    String updateReserveTable(ReserveRequest reserveRequest);


    String deleteReserveTable(ReserveRequest reserveRequest);
}
