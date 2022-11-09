package com.hrs.hotelreservationsystem.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "table_queue")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Queue {
    @Id
    @Column(name = "queue_id")
    @JsonIgnore
    private Integer id;
    @Column(name = "table_id")
    private Integer tableId;
    @Column(name = "user_id")
    @JsonIgnore
    private Integer userId;
    @Column(name = "reserve_time")
    private Date reserveTime;
    @Column(name = "party_size")
    private Integer partySize;
    @ManyToOne
    @JoinColumn(name = "user_id",insertable = false, updatable = false)
    @JsonIgnore
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "table_id",insertable = false, updatable = false)
    @JsonIgnore
    private com.hrs.hotelreservationsystem.pojo.Table table;
}
