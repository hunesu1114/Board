package Project.Board.mapper;

import Project.Board.dto.MemberDto;
import Project.Board.dto.PostDto;
import Project.Board.entity.Member;
import Project.Board.entity.Post;
import Project.Board.login.session.SessionConst;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Component
public class Mapper {

    public Post postSave(PostDto dto,HttpServletRequest request) {
        return new Post(dto.getTitle(), dto.getContent(),getMemberFromSession(request));
    }

    //더티체킹
    public Post postUpdate(Post post, PostDto updateParam) {
        //postId와 member는 바뀌지 않음
        post.setTitle(updateParam.getTitle());
        post.setContent(updateParam.getContent());
        return post;
    }

    public Member memberDtoToEntity(MemberDto dto) {
        return new Member(dto.getMemberEmail(), dto.getNickName(), dto.getPassword());
    }

    public MemberDto memberEntityToDto(Member member) {
        return new MemberDto(member.getMemberEmail(), member.getNickName(), member.getPassword());

    }

    public Member getMemberFromSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
    }


}
