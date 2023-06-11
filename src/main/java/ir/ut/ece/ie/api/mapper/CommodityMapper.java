package ir.ut.ece.ie.api.mapper;

import ir.ut.ece.ie.domain.commodity.Commodity;
import ir.ut.ece.ie.api.model.commodity.CommodityDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CommodityMapper {
    @Mapping(target = "provider.id", source = "dto.providerId")
    Commodity toModel(CommodityDTO dto);

    @Mapping(target = "providerId", source = "model.provider.id")
    CommodityDTO toDto(Commodity model);
}
