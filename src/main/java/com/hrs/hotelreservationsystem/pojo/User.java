package com.hrs.hotelreservationsystem.pojo;

import lombok.*;

import javax.persistence.*;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Table(name = "user")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @Column(name = "user_id")
    private Integer id;

    @Column(name = "user_name")
    private String name;

    @Column(name = "user_email")
    private String email;

    @Column(name = "user_phone")
    private String phone;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private Set<Queue> queue = new HashSet<>();

}
