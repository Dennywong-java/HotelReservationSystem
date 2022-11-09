package com.hrs.hotelreservationsystem.pojo.requestbody;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    private String name;

    private String email;

    private String phone;

}
