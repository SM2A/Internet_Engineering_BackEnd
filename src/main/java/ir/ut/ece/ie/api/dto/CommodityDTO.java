package ir.ut.ece.ie.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class CommodityDTO {
    private Long id;
    private String name;
    private Integer providerId;
    private Long price;
    private List<String> categories;
    private Double rating;
    private Integer inStock;
    private String image;
}
