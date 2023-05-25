package ir.ut.ece.ie.api.mapper;

import ir.ut.ece.ie.api.dto.VoteDTO;
import ir.ut.ece.ie.domain.comment.Vote;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface VoteMapper {

    @Mapping(target = "voteId.user.username", source = "dto.username")
    @Mapping(target = "voteId.comment.id", source = "dto.commentId")
    Vote toModel(VoteDTO dto);

    @Mapping(target = "username", source = "model.voteId.user.username")
    @Mapping(target = "commentId", source = "model.voteId.comment.id")
    VoteDTO toDto(Vote model);
}
