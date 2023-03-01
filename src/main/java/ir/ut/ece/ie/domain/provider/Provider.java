package ir.ut.ece.ie.domain.provider;

import lombok.Data;

@Data
public class Provider {
    private Integer id;
    private String name;
    private String registryDate;

    public Provider(Integer id, String name, String registryDate) {
        this.id = id;
        this.name = name;
        this.registryDate = registryDate;
    }
}
