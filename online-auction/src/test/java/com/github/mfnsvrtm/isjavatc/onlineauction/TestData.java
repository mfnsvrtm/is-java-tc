package com.github.mfnsvrtm.isjavatc.onlineauction;

import com.github.mfnsvrtm.isjavatc.onlineauction.entity.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Comparator;
import java.util.List;

public final class TestData {

    private TestData() {

    }

    public final static List<Address> USER_ADDRESSES = List.of(
        new Address(0, "23883 Torphy Shoal Suite 636 Harrisburg, NE 69345"),
        new Address(1, "943030, Самарская область, город Орехово-Зуево, шоссе Чехова, 16"),
        new Address(2, "41 Evans SquareLake AnnaNG5 1HT")
    );

    public final static List<List<Group>> USER_GROUPS = List.of(
        List.of(),
        List.of(),
        List.of(new Group(0, "administrator"))
    );

    public final static List<User> USERS = List.of(
        new User(0, "shanahan.lavern", "{encoded}'C?T4e'@[Y[", "Madie", "Leannon", "emmerich.rex@okeefe.com", "(680) 626-3764", USER_ADDRESSES.get(0), USER_GROUPS.get(0), null, null),
        new User(1, "nazar15", "{encoded}(7$cE(\"&T+M6T&wP7A?", "Константин", "Колобов", "rada.titov@rambler.ru", "+7 (922) 662-9075", USER_ADDRESSES.get(1), USER_GROUPS.get(1), null, null),
        new User(2, "king.william", "{encoded}}^9V+Hd!R!^", "Luke", "Anderson", "sarah.wood@bailey.com", "+44(0)7985 776038", USER_ADDRESSES.get(2), USER_GROUPS.get(2), null, null)
    );

    public final static List<Category> CATEGORIES = List.of(
        new Category(0, "category 0"),
        new Category(1, "category 1")
    );

    public static final List<Item> ITEMS = List.of(
        new Item(0, "item 0", "description 0", "condition 0", CATEGORIES.get(0), USERS.get(0), null),
        new Item(1, "item 1", "description 1", "condition 1", CATEGORIES.get(0), USERS.get(0), null),
        new Item(2, "item 2", "description 2", "condition 2", CATEGORIES.get(1), USERS.get(1), null)
    );

    public static final List<Lot> LOTS = List.of(
        new Lot(0, BigDecimal.valueOf(50), BigDecimal.ONE, OffsetDateTime.of(LocalDateTime.of(2022, 12, 20, 0, 0), ZoneOffset.UTC), OffsetDateTime.of(LocalDateTime.of(2023, 1, 10, 0, 0), ZoneOffset.UTC), ITEMS.get(0), null, null),
        new Lot(1, BigDecimal.valueOf(100), BigDecimal.TEN, OffsetDateTime.of(LocalDateTime.of(2022, 12, 21, 0, 0), ZoneOffset.UTC), OffsetDateTime.of(LocalDateTime.of(2022, 1, 5, 0, 0), ZoneOffset.UTC), ITEMS.get(1), null, null),
        new Lot(2, BigDecimal.valueOf(5), BigDecimal.TWO, OffsetDateTime.of(LocalDateTime.of(2022, 12, 18, 0, 0), ZoneOffset.UTC), OffsetDateTime.of(LocalDateTime.of(2022, 1, 3, 0, 0), ZoneOffset.UTC), ITEMS.get(2), null, null)
    );

    public static final List<Bid> BIDS = List.of(
        new Bid(0, BigDecimal.valueOf(1000), OffsetDateTime.of(LocalDateTime.of(2022, 12, 27, 0, 0), ZoneOffset.UTC), false, LOTS.get(0), USERS.get(2))
    );

    static {
        USERS.get(0).setItems(List.of(ITEMS.get(0), ITEMS.get(1)));
        USERS.get(1).setItems(List.of(ITEMS.get(2)));
        USERS.get(2).setItems(List.of());

        USERS.forEach(user -> user.setBids(BIDS.stream().filter(bid -> bid.getBidder() == user).toList()));

        ITEMS.get(0).setLot(LOTS.get(0));
        ITEMS.get(1).setLot(LOTS.get(1));
        ITEMS.get(2).setLot(LOTS.get(2));

        LOTS.forEach(lot -> {
            var bids = BIDS.stream().filter(bid -> bid.getLot() == lot).toList();
            lot.setBids(bids);
            lot.setWinningBid(bids.stream()
                    .filter(bid -> !bid.getRetracted())
                    .max(Comparator.comparing(Bid::getAmount))
                    .orElse(null));
        });
    }

}
