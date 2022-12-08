package com.github.mfnsvrtm.isjavatc.task3.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Lot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @OneToOne
    private Item item;
    @NotNull
    private BigDecimal startingPrice;
    @NotNull
    private BigDecimal minimumBidIncrement;
    @NotNull
    private OffsetDateTime auctionStart;
    @NotNull
    private OffsetDateTime auctionEnd;
    @OneToOne
    @ToString.Exclude
    private Bid winningBid;

    @OneToMany(mappedBy = "lot")
    @ToString.Exclude
    private List<Bid> bids;

    @ToString.Include(name = "winningBid.id")
    private int winningBidToString() {
        return getWinningBid().getId();
    }

}
