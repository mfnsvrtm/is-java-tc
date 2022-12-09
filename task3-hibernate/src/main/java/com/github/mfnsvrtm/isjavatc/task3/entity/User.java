package com.github.mfnsvrtm.isjavatc.task3.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "\"user\"")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Integer id;
    @NotNull
    @Size(max = 30)
    private String username;
    @NotNull
    @Size(max = 30)
    private String password;
    @NotNull
    @Size(max = 30)
    private String firstName;
    @NotNull
    @Size(max = 30)
    private String lastName;
    @NotNull
    @Size(max = 100)
    private String email;
    @NotNull
    @Size(max = 30)
    private String phoneNumber;
    @NotNull
    @ManyToOne
    private Address address;
    @ManyToMany
    @JoinTable(
            name = "user_group",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "group_id")}
    )
    @ToString.Exclude
    private List<Group> groups;

    @OneToMany(mappedBy = "seller")
    @ToString.Exclude
    List<Item> items;
    @OneToMany(mappedBy = "bidder")
    @ToString.Exclude
    List<Bid> bids;

}
