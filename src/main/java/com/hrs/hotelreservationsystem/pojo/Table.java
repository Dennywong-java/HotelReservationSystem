package com.hrs.hotelreservationsystem.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@javax.persistence.Table(name = "table_detail")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Table {

    @Id
    @Column(name = "table_id")
    private Integer id;

    @Column(name = "des")
    private String description;

    @Column(name = "is_available")
    private Byte is_available;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "table")
    private Set<Queue> queue = new HashSet<>();
}
