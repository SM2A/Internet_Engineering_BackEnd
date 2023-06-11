package ir.ut.ece.ie.api.mapper;

import ir.ut.ece.ie.api.model.comment.CommentDTO;
import ir.ut.ece.ie.domain.comment.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CommentMapper {
    @Mapping(target = "commodity.id", source = "dto.commodityId")
    @Mapping(target = "user.username", source = "dto.username")
    Comment toModel(CommentDTO dto);

    @Mapping(target = "commodityId", source = "model.commodity.id")
    @Mapping(target = "username", source = "model.user.username")
    CommentDTO toDto(Comment model);
}
