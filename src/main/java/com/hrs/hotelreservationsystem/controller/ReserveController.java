package com.hrs.hotelreservationsystem.controller;


import com.hrs.hotelreservationsystem.pojo.requestbody.ReserveRequest;
import com.hrs.hotelreservationsystem.service.impl.ReserveServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/reserve")
public class ReserveController {

    @Autowired
    private ReserveServiceImpl reserveServiceImpl;

    // create reservation
    @RequestMapping(value = "/table", method = RequestMethod.POST)
    public String reserveTables(@RequestBody ReserveRequest reserveRequest) {
        return reserveServiceImpl.reserveTable(reserveRequest);
    }

    // update reservation
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public String updateReserveTables(@RequestBody ReserveRequest reserveRequest) {
        return reserveServiceImpl.updateReserveTable(reserveRequest);
    }

    //delete reservation
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public String deleteReservation(@RequestBody ReserveRequest reserveRequest) {
        return reserveServiceImpl.deleteReserveTable(reserveRequest);
    }

}
