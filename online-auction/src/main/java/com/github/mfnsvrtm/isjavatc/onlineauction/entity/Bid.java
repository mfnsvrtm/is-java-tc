package com.github.mfnsvrtm.isjavatc.onlineauction.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    private BigDecimal amount;
    @NotNull
    private OffsetDateTime time;
    @NotNull
    private Boolean retracted;

    @NotNull
    @ManyToOne
    @ToString.Exclude
    private Lot lot;
    @NotNull
    @ManyToOne
    @ToString.Exclude
    private User bidder;

    @ToString.Include(name = "lot.id")
    private int lotToString() {
        return getLot().getId();
    }

    @ToString.Include(name = "bidder.id")
    private int bidderToString() {
        return getBidder().getId();
    }

    public Bid(BigDecimal amount, Lot lot, User bidder) {
        this.amount = amount;
        this.time = OffsetDateTime.now(ZoneOffset.UTC);
        this.retracted = false;

        this.lot = lot;
        this.bidder = bidder;
    }

}
