package com.github.mfnsvrtm.isjavatc.task3;

import com.github.mfnsvrtm.isjavatc.task3.dao.*;
import com.github.mfnsvrtm.isjavatc.task3.entity.Category;
import com.github.mfnsvrtm.isjavatc.task3.entity.Item;
import com.github.mfnsvrtm.isjavatc.task3.entity.Lot;
import com.github.mfnsvrtm.isjavatc.task3.entity.User;
import com.github.mfnsvrtm.isjavatc.task3.repository.*;
import org.testng.annotations.Test;

public class DaoTest {
    @Test
    public void daoTest() {
        LotDao lotDao = new LotRepository();
        ItemDao itemDao = new ItemRepository();
        CategoryDao categoryDao = new CategoryRepository();
        BidDao bidDao = new BidRepository();
        PurchaseDao purchaseDao = new PurchaseRepository();
        UserDao userDao = new UserRepository();

        Lot lot0 = lotDao.getById(0).get();
        Item item0 = itemDao.getById(0).get();
        Category category0 = categoryDao.getById(0).get();
        User user0 = userDao.getById(0).get();
        User user1 = userDao.getById(1).get();

        System.out.printf(" -- bids in %s:%n", lot0);
        bidDao.getByLot(lot0).forEach(System.out::println);
        System.out.printf(" -- bids by %s:%n", user0);
        bidDao.getByUser(user0).forEach(System.out::println);
        System.out.printf(" -- bids by %s:%n", user1);
        bidDao.getByUser(user1).forEach(System.out::println);

        System.out.printf(" -- items by %s:%n", user0);
        itemDao.getByUser(user0).forEach(System.out::println);

        System.out.printf(" -- lots by %s:%n", user0);
        lotDao.getByUser(user0).forEach(System.out::println);
        System.out.printf(" -- lots by %s:%n", category0);
        lotDao.getByCategory(category0).forEach(System.out::println);

        System.out.printf(" -- purchases by %s:%n", user0);
        purchaseDao.getByUser(user0).forEach(System.out::println);

        itemDao.updateName(item0, "name");
        itemDao.updateDescription(item0, "idk");

        System.out.printf(" -- items by %s:%n", user0);
        itemDao.getByUser(user0).forEach(System.out::println);
    }
}
