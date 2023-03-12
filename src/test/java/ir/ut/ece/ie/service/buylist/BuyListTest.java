package ir.ut.ece.ie.service.buylist;

import ir.ut.ece.ie.domain.commodity.Commodity;
import ir.ut.ece.ie.domain.user.User;
import ir.ut.ece.ie.repository.buylist.BuyListRepository;
import ir.ut.ece.ie.repository.buylist.BuyListRepositoryImpl;
import ir.ut.ece.ie.repository.commodity.CommodityRepository;
import ir.ut.ece.ie.repository.commodity.CommodityRepositoryImpl;
import ir.ut.ece.ie.repository.user.UserRepository;
import ir.ut.ece.ie.repository.user.UserRepositoryImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BuyListTest {

    private BuyListService buyListService;

    @BeforeEach
    public void initialization() {
        long id = 1;

        CommodityRepository commodityRepository = new CommodityRepositoryImpl();
        for (long i = 1; i <= 5; i++) {
            commodityRepository.save(new Commodity(id, "abc" + id, 1, 1000L, List.of("AA"), 5.0D, 10));
            id++;
        }
        for (long i = 1; i <= 5; i++) {
            commodityRepository.save(new Commodity(id, "abc" + id, 1, 1000L, List.of("BB"), 5.0D, 10));
            id++;
        }
        for (long i = 1; i <= 5; i++) {
            commodityRepository.save(new Commodity(id, "abc" + id, 1, 1000L, List.of("CC"), 5.0D, 10));
            id++;
        }

        UserRepository userRepository = UserRepositoryImpl.getInstance();
        userRepository.save(new User("123", "asd", "a@a.com", "1977-09-15", "Tehran", 10000L));
        userRepository.save(new User("321", "asd", "a@a.com", "1977-09-15", "Tehran", 10000L));

        BuyListRepository buyListRepository = new BuyListRepositoryImpl();

        buyListService = new BuyListServiceImpl(userRepository, commodityRepository, buyListRepository);
    }

    @AfterEach
    public void cleanUp() {
        buyListService = null;
    }

    @Test
    public void empty_buy_list() {
        assertEquals(buyListService.getBuyList("123").getCommodities().size(), 0);
    }

    @Test
    public void one_item_buy_list() {
        buyListService.addToBuyList("123", 2L);
        assertEquals(buyListService.getBuyList("123").getCommodities().size(), 1);
    }

    @Test
    public void multiple_item_buy_list() {
        buyListService.addToBuyList("123", 2L);
        buyListService.addToBuyList("123", 4L);
        buyListService.addToBuyList("123", 7L);
        assertEquals(buyListService.getBuyList("123").getCommodities().size(), 3);
    }
}
