package ir.ut.ece.ie.service.commodity;

import ir.ut.ece.ie.domain.commodity.Commodity;
import ir.ut.ece.ie.domain.provider.Provider;
import ir.ut.ece.ie.repository.commodity.CommodityRepositoryImpl;
import ir.ut.ece.ie.repository.commodity.ScoreRepositoryImpl;
import ir.ut.ece.ie.repository.provider.ProviderRepository;
import ir.ut.ece.ie.repository.provider.ProviderRepositoryImpl;
import ir.ut.ece.ie.repository.user.UserRepositoryImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class CommodityTest {

    CommodityService commodityService;

    ProviderRepository providerRepository;

    @BeforeEach
    public void initialization() {
        providerRepository = new ProviderRepositoryImpl();
        providerRepository.save(new Provider(1, "a", "2023-09-15"));

        commodityService = new CommodityServiceImpl(
                new CommodityRepositoryImpl(),
                new ScoreRepositoryImpl(),
                providerRepository,
                new UserRepositoryImpl()
        );
    }

    @AfterEach
    public void cleanUp() {
        commodityService = null;
        providerRepository = null;
    }

    @Test
    public void no_item_not_found() {
        assertNull(commodityService.getCommodityById(5L));
    }

    @Test
    public void one_item_found() {
        commodityService.addCommodity(new Commodity(1L, "abc", 1, 1L, List.of("AA"), 5.0D, 10));

        assertEquals(commodityService.getCommodityById(1L).getId(), 1);
    }

    @Test
    public void multiple_item_found() {
        commodityService.addCommodity(new Commodity(1L, "abc", 1, 1L, List.of("AA"), 5.0D, 10));
        commodityService.addCommodity(new Commodity(2L, "abc", 1, 1L, List.of("AA"), 5.0D, 10));
        commodityService.addCommodity(new Commodity(3L, "abc", 1, 1L, List.of("AA"), 5.0D, 10));
        commodityService.addCommodity(new Commodity(4L, "abc", 1, 1L, List.of("AA"), 5.0D, 10));

        assertEquals(commodityService.getCommodityById(1L).getId(), 1);
    }

    @Test
    public void multiple_item_not_found() {
        commodityService.addCommodity(new Commodity(1L, "abc", 1, 1L, List.of("AA"), 5.0D, 10));
        commodityService.addCommodity(new Commodity(2L, "abc", 1, 1L, List.of("AA"), 5.0D, 10));
        commodityService.addCommodity(new Commodity(3L, "abc", 1, 1L, List.of("AA"), 5.0D, 10));
        commodityService.addCommodity(new Commodity(4L, "abc", 1, 1L, List.of("AA"), 5.0D, 10));

        assertNull(commodityService.getCommodityById(5L));
    }

}
