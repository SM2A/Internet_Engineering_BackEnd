package ir.ut.ece.ie.domain.commodity;

import lombok.Data;

import java.util.List;

@Data
public class Commodity {
    private Long id;
    private String name;
    private Integer providerId;
    private Long price;
    private List<String> categories;
    private Double rating;
    private Integer rateCount;
    private Integer inStock;
    private String image;

    public Commodity(Long id, String name, Integer providerId, Long price, List<String> categories, Double rating, Integer inStock) {
        this.id = id;
        this.name = name;
        this.providerId = providerId;
        this.price = price;
        this.categories = categories;
        this.rating = rating;
        this.inStock = inStock;
    }
}
