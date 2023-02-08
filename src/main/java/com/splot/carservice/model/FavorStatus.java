package com.splot.carservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "favor_statuses")
public class FavorStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private StatusName status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StatusName getStatus() {
        return status;
    }

    public void setStatus(StatusName status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "FavorStatus{" +
                "id=" + id +
                ", status=" + status +
                '}';
    }

    private enum StatusName {
        PAID,
        NOT_PAID
    }
}
