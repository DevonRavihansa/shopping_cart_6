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
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    @NonNull
    private String sku;

    @Column(nullable = false)
    @NonNull
    private String name;

    @Column(nullable = false)
    @NonNull
    private double price;

    @Column(nullable = false, columnDefinition = "varchar(255) default '/images/img.jpg'")
    private String image;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, insertable = false, updatable = false, columnDefinition = "timestamp default current_timestamp")
    private Date created;

    @OneToMany(mappedBy = "product")
    private Set<OrderItem> orderItems = new HashSet<>(0);
}
