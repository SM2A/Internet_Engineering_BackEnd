package ir.ut.ece.ie.api.mapper;

import ir.ut.ece.ie.api.model.cart.BuyItemDTO;
import ir.ut.ece.ie.domain.cart.BuyItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BuyItemMapper {
    @Mapping(target = "id.user.username", source = "dto.username")
    @Mapping(target = "id.commodity.id", source = "dto.commodityId")
    BuyItem toModel(BuyItemDTO dto);

    @Mapping(target = "username", source = "model.id.user.username")
    @Mapping(target = "commodityId", source = "model.id.commodity.id")
    BuyItemDTO toDto(BuyItem model);

}
