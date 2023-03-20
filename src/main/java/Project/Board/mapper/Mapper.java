package Project.Board.mapper;

import Project.Board.dto.MemberDto;
import Project.Board.dto.PostDto;
import Project.Board.entity.Member;
import Project.Board.entity.Post;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    public Post postDtoToEntity(PostDto dto) {
        return new Post(dto.getAuthor(), dto.getTitle(), dto.getContent());
    }

    //더티체킹
    public Post postUpdate(Post post, PostDto updateParam) {
        //postId와 member는 바뀌지 않음
        post.setTitle(updateParam.getTitle());
        post.setContent(updateParam.getContent());
        return post;
    }

    public Member memberDtoToEntity(MemberDto dto) {
        return new Member(dto.getNickName(), dto.getMemberEmail(), dto.getPassword());
    }

}
