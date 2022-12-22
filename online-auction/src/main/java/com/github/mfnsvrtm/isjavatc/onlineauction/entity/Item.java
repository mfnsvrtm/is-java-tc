package com.github.mfnsvrtm.isjavatc.onlineauction.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    @Size(max = 50)
    private String name;
    private String description;
    @NotNull
    @Size(max = 50)
    private String condition;

    @ManyToOne
    private Category category;
    @NotNull
    @ManyToOne
    @ToString.Exclude
    private User seller;

    @OneToOne(mappedBy = "item")
    @ToString.Exclude
    private Lot lot;

    @ToString.Include(name = "seller.id")
    private int sellerToString() {
        return getSeller().getId();
    }

}
