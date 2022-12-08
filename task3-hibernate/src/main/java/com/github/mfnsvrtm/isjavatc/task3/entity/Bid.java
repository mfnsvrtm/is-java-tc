package com.github.mfnsvrtm.isjavatc.task3.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @ManyToOne
    @ToString.Exclude
    private Lot lot;
    @NotNull
    @ManyToOne
    @ToString.Exclude
    private User bidder;
    @NotNull
    private BigDecimal amount;
    @NotNull
    private OffsetDateTime time;
    @NotNull
    private boolean retracted;

    @ToString.Include(name = "lot.id")
    private int lotToString() {
        return getLot().getId();
    }

    @ToString.Include(name = "bidder.id")
    private int bidderToString() {
        return getBidder().getId();
    }

}
