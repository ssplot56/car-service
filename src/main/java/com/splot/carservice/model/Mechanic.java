package com.splot.carservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "mechanics")
public class Mechanic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "full_name")
    private String fullName;
    @ManyToMany
    @JoinTable(name = "mechanics_completed_orders",
            joinColumns = @JoinColumn(name = "mechanic_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id"))
    private List<Order> completeOrders;
}
