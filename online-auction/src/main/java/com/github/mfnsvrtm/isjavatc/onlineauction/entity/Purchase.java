package com.github.mfnsvrtm.isjavatc.onlineauction.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    private BigDecimal amount;
    @NotNull
    @Size(max = 30)
    private String paymentMethod;
    @NotNull
    @Size(max = 100)
    private String bankAccountNumber;

    @NotNull
    @OneToOne
    private Bid bid;

}
