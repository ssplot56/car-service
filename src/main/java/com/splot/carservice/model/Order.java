package com.splot.carservice.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Car car;
    private String description;
    @Column(name = "acceptance_date")
    private LocalDateTime acceptDate;
    @OneToMany
    @JoinTable(name = "orders_favors",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "favor_id"))
    private List<Favor> favors;
    @OneToMany
    @JoinTable(name = "orders_products",
            joinColumns = @JoinColumn(name= "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;
    @Enumerated(EnumType.STRING)
    private StatusName status;
    private Double price;
    @Column(name = "completion_date")
    private LocalDateTime completeDate;

    public enum StatusName {
        ACCEPT("Accept"),
        IN_PROCESS("In process"),
        SUCCESSFUL_DONE("Successful completed"),
        UNSUCCESSFUL_DONE("Unsuccessful completed"),
        PAID("Paid");
        private String value;

        StatusName(String value) {
        }
    }
}
