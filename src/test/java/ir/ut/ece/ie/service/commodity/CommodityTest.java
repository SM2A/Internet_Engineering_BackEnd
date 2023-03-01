package ir.ut.ece.ie.service.commodity;

import ir.ut.ece.ie.domain.commodity.Commodity;
import ir.ut.ece.ie.domain.commodity.Score;
import ir.ut.ece.ie.domain.provider.Provider;
import ir.ut.ece.ie.domain.user.User;
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

    long lastItem;
    CommodityService commodityService;

    @BeforeEach
    public void initialization() {
        lastItem = 1;
        ProviderRepository providerRepository = new ProviderRepositoryImpl();
        providerRepository.save(new Provider(1, "a", "2023-09-15"));

        UserRepository userRepository = new UserRepositoryImpl();
        userRepository.save(new User("123", "asd", "a@a.com", "1977-09-15", "Tehran", 10000L));

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
    public void add_rating_higher_than_now() {
        addCommodity(1, List.of("AA"));
        commodityService.rateCommodity(new Score("123", 1L, 10));
        assertEquals(commodityService.getCommodityById(1L).getRating(), 7.5D);
    }

    private void addCommodity(int count, List<String> category) {
        for (long i = 1; i <= count; i++) {
            commodityService.addCommodity(new Commodity(lastItem, "abc"+lastItem, 1, 1000L, category, 5.0D, 10));
            lastItem++;
        }
    }
}
