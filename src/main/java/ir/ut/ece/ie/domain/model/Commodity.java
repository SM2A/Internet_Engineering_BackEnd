package ir.ut.ece.ie.domain.model;

import java.util.List;

public class Commodity {
    private Long id;
    private String name;
    private Integer providerId;
    private Long price;
    private List<String> categories;
    private Double rating;
    private Integer inStock;
}
