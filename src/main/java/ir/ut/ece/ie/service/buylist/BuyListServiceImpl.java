package ir.ut.ece.ie.service.buylist;

import ir.ut.ece.ie.domain.buylist.BuyList;
import ir.ut.ece.ie.domain.buylist.Discount;
import ir.ut.ece.ie.domain.commodity.Commodity;
import ir.ut.ece.ie.domain.user.User;
import ir.ut.ece.ie.exception.OnlineShopException;
import ir.ut.ece.ie.repository.buylist.BuyListRepository;
import ir.ut.ece.ie.repository.commodity.CommodityRepository;
import ir.ut.ece.ie.repository.user.DiscountRepository;
import ir.ut.ece.ie.repository.user.UserRepository;

import java.util.Optional;

public class BuyListServiceImpl implements BuyListService {
    private final UserRepository userRepository;
    private final CommodityRepository commodityRepository;
    private final BuyListRepository buyListRepository;
    private final DiscountRepository discountRepository;

    public BuyListServiceImpl(UserRepository userRepository, CommodityRepository commodityRepository,
                              BuyListRepository buyListRepository, DiscountRepository discountRepository) {
        this.userRepository = userRepository;
        this.commodityRepository = commodityRepository;
        this.buyListRepository = buyListRepository;
        this.discountRepository = discountRepository;
    }

    @Override
    public Commodity addToBuyList(String username, Long commodityId) {
        userRepository.findById(username).orElseThrow(() -> new OnlineShopException("User not found"));
        Commodity commodity = commodityRepository.findById(commodityId)
                .orElseThrow(() -> new OnlineShopException("Commodity doesn't exist"));
        if (commodity.getInStock() <= 0)
            throw new OnlineShopException("Not enough commodity in stock");
        Optional<BuyList> userBuyList = buyListRepository.findByUsername(username);
        if (userBuyList.isPresent() && userBuyList.get().getCommodities().contains(commodity))
            throw new OnlineShopException("Commodity already exist in buy list");
        buyListRepository.saveCommodityToList(username, commodity);
        return commodity;
    }

    @Override
    public Optional<BuyList> getBuyList(String username) {
        return buyListRepository.findByUsername(username);
    }

    @Override
    public void removeFromBuyList(String username, Long commodityId) {
        userRepository.findById(username).orElseThrow(() -> new OnlineShopException("User not found"));
        Commodity commodity = commodityRepository.findById(commodityId)
                .orElseThrow(() -> new OnlineShopException("Commodity not found"));
        buyListRepository.removeCommodity(username, commodity);
    }

    @Override
    public void applyDiscount(BuyList buyList, String code) {
        User user = userRepository.findById(buyList.getUsername()).orElseThrow(() -> new OnlineShopException("User not found"));
        Discount discount = discountRepository.findByCode(code)
                .orElseThrow(() -> new OnlineShopException("Discount not found"));
        if (user.getUsedDiscounts().contains(discount))
            throw new OnlineShopException("The discount code has expired");
        buyList.setDiscount(discount);
    }

    @Override
    public void pay(BuyList buyList) {
        User user = userRepository.findById(buyList.getUsername()).orElseThrow(() -> new OnlineShopException("User not found"));
        long price = buyList.getPrice();
        if (user.getCredit() - price < 0)
            throw new OnlineShopException("Insufficient credit");
        user.setCredit(user.getCredit() - price);
        user.getUsedDiscounts().add(buyList.getDiscount());
        buyList.setDiscount(null);
        buyList.getCommodities().forEach(commodity -> commodity.setInStock(commodity.getInStock() - 1));
    }
}
