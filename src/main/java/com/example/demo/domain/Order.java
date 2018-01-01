package com.example.demo.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Devon Ravihansa on 10/9/2017.
 */
@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    @NonNull
    private String poNumber;

    @ManyToOne
    @JoinColumn(nullable = false)
    @NonNull
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus orderStatus = OrderStatus.CART;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, insertable = false, updatable = false, columnDefinition = "timestamp default current_timestamp")
    private Date created;

    @OneToMany(mappedBy = "order")
    private Set<OrderItem> orderItems = new HashSet<>(0);
}