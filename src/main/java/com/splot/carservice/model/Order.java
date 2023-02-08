package com.splot.carservice.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Car car;
    private String problemDescription;
    private LocalDateTime acceptDate;
    @OneToMany
    private List<Favor> favors;
    @OneToMany
    private List<MachineComponent> components;
    @ManyToOne
    private OrderStatus status;
    private Double finalCost;
    private LocalDateTime completeDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    public LocalDateTime getAcceptDate() {
        return acceptDate;
    }

    public void setAcceptDate(LocalDateTime acceptDate) {
        this.acceptDate = acceptDate;
    }

    public List<Favor> getFavors() {
        return favors;
    }

    public void setFavors(List<Favor> favors) {
        this.favors = favors;
    }

    public List<MachineComponent> getComponents() {
        return components;
    }

    public void setComponents(List<MachineComponent> components) {
        this.components = components;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Double getFinalCost() {
        return finalCost;
    }

    public void setFinalCost(Double finalCost) {
        this.finalCost = finalCost;
    }

    public LocalDateTime getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(LocalDateTime completeDate) {
        this.completeDate = completeDate;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", car=" + car +
                ", problemDescription='" + problemDescription + '\'' +
                ", acceptDate=" + acceptDate +
                ", favors=" + favors +
                ", components=" + components +
                ", status=" + status +
                ", finalCost=" + finalCost +
                ", completeDate=" + completeDate +
                '}';
    }
}
