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


    private void addCommodity(int count, List<String> category) {
        for (long i = 1; i <= count; i++) {
            commodityService.addCommodity(new Commodity(i, "abc"+i, 1, 1000L, category, 5.0D, 10));
        }
    }
}
