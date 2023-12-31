//package ir.ut.ece.ie.service.buylist;
//
//import ir.ut.ece.ie.controller.buylist.dto.BuyItemReq;
//import ir.ut.ece.ie.domain.commodity.Commodity;
//import ir.ut.ece.ie.domain.user.User;
//import ir.ut.ece.ie.repository.buylist.BuyListRepository;
//import ir.ut.ece.ie.repository.buylist.BuyListRepositoryImpl;
//import ir.ut.ece.ie.repository.commodity.CommodityRepository;
//import ir.ut.ece.ie.repository.commodity.CommodityRepositoryImpl;
//import ir.ut.ece.ie.repository.user.DiscountRepository;
//import ir.ut.ece.ie.repository.user.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//@ExtendWith(MockitoExtension.class)
//@RequiredArgsConstructor
//public class BuyListTest {
//    @Mock
//    private DiscountRepository discountRepository;
//    private final UserRepository userRepository;
//    private BuyListService buyListService;
//
//    @BeforeEach
//    public void initialization() {
//        long id = 1;
//
//        CommodityRepository commodityRepository = new CommodityRepositoryImpl();
//        for (long i = 1; i <= 5; i++) {
//            commodityRepository.save(new Commodity(id, "abc" + id, 1, 1000L, List.of("AA"), 5.0D, 10));
//            id++;
//        }
//        for (long i = 1; i <= 5; i++) {
//            commodityRepository.save(new Commodity(id, "abc" + id, 1, 1000L, List.of("BB"), 5.0D, 10));
//            id++;
//        }
//        for (long i = 1; i <= 5; i++) {
//            commodityRepository.save(new Commodity(id, "abc" + id, 1, 1000L, List.of("CC"), 5.0D, 10));
//            id++;
//        }
//
//        userRepository.save(new User("123", "asd", "a@a.com", "1977-09-15", "Tehran", 10000L, List.of()));
//        userRepository.save(new User("321", "asd", "a@a.com", "1977-09-15", "Tehran", 10000L, List.of()));
//
//        BuyListRepository buyListRepository = new BuyListRepositoryImpl();
//        buyListService = new BuyListServiceImpl(userRepository, commodityRepository, buyListRepository, discountRepository);
//    }
//
//    @AfterEach
//    public void cleanUp() {
//        buyListService = null;
//    }
//
//    @Test
//    public void empty_buy_list() {
//        assertTrue(buyListService.getBuyList("123").isEmpty());
//    }
//
//    @Test
//    public void one_item_buy_list() {
//        buyListService.addToBuyList("123", new BuyItemReq(2L, 1));
//        assertEquals(buyListService.getBuyList("123").get().getBuyItems().size(), 1);
//    }
//
//    @Test
//    public void multiple_item_buy_list() {
//        buyListService.addToBuyList("123", new BuyItemReq(2L, 1));
//        buyListService.addToBuyList("123", new BuyItemReq(4L, 1));
//        buyListService.addToBuyList("123", new BuyItemReq(7L, 1));
//        assertEquals(buyListService.getBuyList("123").get().getBuyItems().size(), 3);
//    }
//}
