package com.hrs.hotelreservationsystem.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Table;
import javax.persistence.*;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

@Table(name = "user")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements Comparable<User> {

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
    @OrderBy("tableId ASC")
    private Set<Queue> queue = new HashSet<>();

    @Override
    public int compareTo(User o) {
        return this.getId()-o.getId();
    }


    //重写比较器方法并按照id排序

}
