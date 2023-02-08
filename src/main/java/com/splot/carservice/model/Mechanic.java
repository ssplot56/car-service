package com.splot.carservice.model;

import javax.persistence.*;
import java.util.List;

@Entity(name = "mechanics")
public class Mechanic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    @OneToMany
    private List<Order> completeOrders;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<Order> getCompleteOrders() {
        return completeOrders;
    }

    public void setCompleteOrders(List<Order> completeOrders) {
        this.completeOrders = completeOrders;
    }

    @Override
    public String toString() {
        return "Mechanic{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", completeOrders=" + completeOrders +
                '}';
    }
}
