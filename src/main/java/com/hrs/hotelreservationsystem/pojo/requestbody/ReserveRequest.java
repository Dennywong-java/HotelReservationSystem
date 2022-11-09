package com.hrs.hotelreservationsystem.pojo.requestbody;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReserveRequest {

    private String email;

    private List<Integer> tableId;

    private Integer partySize;


}
