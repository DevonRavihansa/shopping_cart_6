package com.example.demo.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Devon Ravihansa on 10/9/2017.
 */
@Entity
@Table(name = "userroles")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class UserRole {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    @NonNull
    private String role;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
    private Set<User> users = new HashSet<>(0);
}
