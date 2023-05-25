package ir.ut.ece.ie.domain.commodity;

import ir.ut.ece.ie.domain.provider.Provider;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Commodity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "provider_id", nullable = false)
    private Provider provider;

    @Column
    private Long price;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "categories", joinColumns = @JoinColumn(name = "commodity_id"))
    @Column(name = "category")
    private List<String> categories = new ArrayList<>();

    @Column
    private Double rating;

    @Column
    private Integer rateCount;

    @Column
    private Integer inStock;

    @Column(length = 512)
    private String image;
}
