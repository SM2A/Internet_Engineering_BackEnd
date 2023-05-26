package ir.ut.ece.ie.api.mapper;

import ir.ut.ece.ie.api.dto.ScoreDTO;
import ir.ut.ece.ie.domain.commodity.Score;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ScoreMapper {
    @Mapping(target = "scoreId.user.username", source = "dto.username")
    @Mapping(target = "scoreId.commodity.id", source = "dto.commodityId")
    Score toModel(ScoreDTO dto);

    @Mapping(target = "username", source = "model.scoreId.user.username")
    @Mapping(target = "commodityId", source = "model.scoreId.commodity.id")
    ScoreDTO toDto(Score model);
}
