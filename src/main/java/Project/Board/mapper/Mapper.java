package Project.Board.mapper;

import Project.Board.dto.MemberDto;
import Project.Board.dto.PostDto;
import Project.Board.entity.Member;
import Project.Board.entity.Post;

public class Mapper {

    public Post postDtoToEntity(PostDto dto) {
        return new Post(dto.getMember(), dto.getTitle(), dto.getContent());
    }

    //더티체킹
    public Post postUpdate(Post post, PostDto updateParam) {
        //postId와 member는 바뀌지 않음
        post.setTitle(updateParam.getTitle());
        post.setContent(updateParam.getContent());
        return post;
    }

    public Member memberDtoToEntity(MemberDto dto) {
        return new Member(dto.getMemberName(), dto.getMemberEmail(), dto.getPassword(), dto.getPosts());
    }

    public Member memberUpdate(Member member, MemberDto updateParam) {
        //이메일, 패스워드 바뀌지않음
        member.setMemberName(updateParam.getMemberName());
        member.setPosts(updateParam.getPosts());
        return member;
    }
}
