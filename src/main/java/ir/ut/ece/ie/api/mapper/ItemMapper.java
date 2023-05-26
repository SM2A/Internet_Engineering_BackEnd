package ir.ut.ece.ie.api.mapper;

import ir.ut.ece.ie.api.dto.ItemDTO;
import ir.ut.ece.ie.domain.cart.Item;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = CommodityMapper.class)
public interface ItemMapper {

    ItemDTO toDto(Item model);
}
