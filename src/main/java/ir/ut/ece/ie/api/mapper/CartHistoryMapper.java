package ir.ut.ece.ie.api.mapper;

import ir.ut.ece.ie.api.dto.CartHistoryDTO;
import ir.ut.ece.ie.domain.cart.CartHistory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = ItemMapper.class)
public interface CartHistoryMapper {
    @Mapping(target = "username", source = "user.username")
    CartHistoryDTO toDto(CartHistory model);
}
