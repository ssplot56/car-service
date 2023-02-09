package com.splot.carservice.model;

import javax.persistence.*;

@Entity(name = "favors")
public class Favor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Order order;
    @ManyToOne
    private Mechanic mechanic;
    private Double cost;
    private StatusName status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Mechanic getMechanic() {
        return mechanic;
    }

    public void setMechanic(Mechanic mechanic) {
        this.mechanic = mechanic;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public StatusName getStatus() {
        return status;
    }

    public void setStatus(StatusName status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Favor{" +
                "id=" + id +
                ", order=" + order +
                ", mechanic=" + mechanic +
                ", cost=" + cost +
                ", status=" + status +
                '}';
    }

    public enum StatusName {
        PAID,
        NOT_PAID
    }
}
