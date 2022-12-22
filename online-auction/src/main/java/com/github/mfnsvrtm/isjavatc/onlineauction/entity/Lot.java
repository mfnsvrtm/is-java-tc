package com.github.mfnsvrtm.isjavatc.onlineauction.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Lot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    private BigDecimal startingPrice;
    @NotNull
    private BigDecimal minimumBidIncrement;
    @NotNull
    private OffsetDateTime auctionStart;
    @NotNull
    private OffsetDateTime auctionEnd;

    @NotNull
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Item item;
    @OneToOne
    @ToString.Exclude
    private Bid winningBid;

    @OneToMany(mappedBy = "lot", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    private List<Bid> bids;

    @ToString.Include(name = "winningBid.id")
    private int winningBidToString() {
        return getWinningBid().getId();
    }

    public BigDecimal getCurrentPrice() {
        return winningBid != null ? winningBid.getAmount() : startingPrice;
    }

}
