package com.github.mfnsvrtm.isjavatc.task3;

import com.github.mfnsvrtm.isjavatc.task3.dao.*;
import com.github.mfnsvrtm.isjavatc.task3.entity.Category;
import com.github.mfnsvrtm.isjavatc.task3.entity.Item;
import com.github.mfnsvrtm.isjavatc.task3.entity.Lot;
import com.github.mfnsvrtm.isjavatc.task3.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DaoTest {

    @Autowired
    private LotDao lotDao;
    @Autowired
    private ItemDao itemDao;
    @Autowired
    private CategoryDao categoryDao;
    @Autowired
    private BidDao bidDao;
    @Autowired
    private PurchaseDao purchaseDao;
    @Autowired
    private UserDao userDao;

    @Test
    public void daoTest() {
        Lot lot0 = lotDao.findById(0).get();
        Item item0 = itemDao.findById(0).get();
        Category category0 = categoryDao.findById(0).get();
        User user0 = userDao.findById(0).get();
        User user1 = userDao.findById(1).get();

        System.out.printf(" -- bids in %s:%n", lot0);
        bidDao.findByLotId(0).forEach(System.out::println);
        System.out.printf(" -- bids by %s:%n", user0);
        bidDao.findByBidderId(0).forEach(System.out::println);
        System.out.printf(" -- bids by %s:%n", user1);
        bidDao.findByBidderId(0).forEach(System.out::println);

        System.out.printf(" -- items by %s:%n", user0);
        itemDao.findBySellerId(0).forEach(System.out::println);

        System.out.printf(" -- lots by %s:%n", user0);
        lotDao.findByItemSellerId(0).forEach(System.out::println);
        System.out.printf(" -- lots by %s:%n", category0);
        lotDao.findByItemCategoryId(0).forEach(System.out::println);

        System.out.printf(" -- purchases by %s:%n", user0);
        purchaseDao.findByBidBidderId(0).forEach(System.out::println);

        itemDao.updateName(0, "name");
        itemDao.updateDescription(0, "idk");

        System.out.printf(" -- items by %s:%n", user0);
        itemDao.findBySellerId(0).forEach(System.out::println);
    }
}
