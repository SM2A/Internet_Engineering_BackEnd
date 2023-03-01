package ir.ut.ece.ie.service.commodity;

import ir.ut.ece.ie.domain.commodity.Commodity;
import ir.ut.ece.ie.domain.commodity.Score;
import ir.ut.ece.ie.domain.provider.Provider;
import ir.ut.ece.ie.domain.user.User;
import ir.ut.ece.ie.exception.OnlineShopException;
import ir.ut.ece.ie.repository.commodity.CommodityRepositoryImpl;
import ir.ut.ece.ie.repository.commodity.ScoreRepositoryImpl;
import ir.ut.ece.ie.repository.provider.ProviderRepository;
import ir.ut.ece.ie.repository.provider.ProviderRepositoryImpl;
import ir.ut.ece.ie.repository.user.UserRepository;
import ir.ut.ece.ie.repository.user.UserRepositoryImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class CommodityTest {

    private long id;
    private CommodityService commodityService;

    @BeforeEach
    public void initialization() {
        id = 1;
        ProviderRepository providerRepository = new ProviderRepositoryImpl();
        providerRepository.save(new Provider(1, "a", "2023-09-15"));

        UserRepository userRepository = new UserRepositoryImpl();
        userRepository.save(new User("123", "asd", "a@a.com", "1977-09-15", "Tehran", 10000L));
        userRepository.save(new User("456", "asd", "a@a.com", "1977-09-15", "Tehran", 10000L));
        userRepository.save(new User("789", "asd", "a@a.com", "1977-09-15", "Tehran", 10000L));
        userRepository.save(new User("012", "asd", "a@a.com", "1977-09-15", "Tehran", 10000L));

        commodityService = new CommodityServiceImpl(
                new CommodityRepositoryImpl(),
                new ScoreRepositoryImpl(),
                providerRepository,
                userRepository
        );
    }

    @AfterEach
    public void cleanUp() {
        commodityService = null;
    }

    @Test
    public void no_item_not_found() {
        assertNull(commodityService.getCommodityById(5L));
    }

    @Test
    public void one_item_found() {
        addCommodity(1, List.of("AA"));
        assertEquals(commodityService.getCommodityById(1L).getId(), 1);
    }

    @Test
    public void multiple_item_found() {
        addCommodity(4, List.of("AA"));
        assertEquals(commodityService.getCommodityById(1L).getId(), 1);
    }

    @Test
    public void multiple_item_not_found() {
        addCommodity(4, List.of("AA"));
        assertNull(commodityService.getCommodityById(5L));
    }

    @Test
    public void no_item_in_category() {
        addCommodity(4, List.of("AA"));
        assertEquals(commodityService.getCommoditiesByCategory("BB").size(), 0);
    }

    @Test
    public void all_item_in_one_category() {
        addCommodity(4, List.of("AA"));
        assertEquals(commodityService.getCommoditiesByCategory("AA").size(), 4);
    }

    @Test
    public void multiple_item_in_multiple_category() {
        addCommodity(4, List.of("AA"));
        addCommodity(4, List.of("BB"));
        assertEquals(commodityService.getCommoditiesByCategory("AA").size(), 4);
    }

    @Test
    public void multiple_item_with_multiple_category() {
        addCommodity(4, List.of("AA", "CC"));
        addCommodity(4, List.of("BB"));
        assertEquals(commodityService.getCommoditiesByCategory("AA").size(), 4);
    }

    @Test
    public void add_one_rating_valid() {
        addCommodity(1, List.of("AA"));
        commodityService.rateCommodity(new Score("123", 1L, 10));
        assertEquals(commodityService.getCommodityById(1L).getRating(), 10);
    }

    @Test
    public void add_multiple_rating() {
        addCommodity(1, List.of("AA"));
        commodityService.rateCommodity(new Score("123", 1L, 10));
        commodityService.rateCommodity(new Score("456", 1L, 5));
        commodityService.rateCommodity(new Score("789", 1L, 7));
        commodityService.rateCommodity(new Score("012", 1L, 8));
        assertEquals(commodityService.getCommodityById(1L).getRating(), 7.5D);
    }

    @Test
    public void add_one_rating_invalid() {
        addCommodity(1, List.of("AA"));
        commodityService.rateCommodity(new Score("123", 1L, 10));
        assertThrows(OnlineShopException.class,
                () -> commodityService.rateCommodity(new Score("123", 1L, 15)));
    }

    private void addCommodity(int count, List<String> category) {
        for (long i = 1; i <= count; i++) {
            commodityService.addCommodity(new Commodity(id, "abc"+ id, 1, 1000L, category, 5.0D, 10));
            id++;
        }
    }
}
