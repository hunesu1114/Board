package Project.Board.service;

import Project.Board.dto.MemberDto;
import Project.Board.dto.PostDto;
import Project.Board.entity.Member;
import Project.Board.login.session.SessionConst;
import Project.Board.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member saveMember(MemberDto dto) {
        return memberRepository.save(dto);
    }

    public Member findMemberById(Long memberId) {
        return memberRepository.findById(memberId);
    }

    public Member loginValidation(String memberEmail, String pw) {
        Member loginMember = memberRepository.findByEmail(memberEmail);
        if (loginMember.getMemberEmail().equals(memberEmail) && loginMember.getPassword().equals(pw)) {
            return loginMember;
        } else {
            return null;
        }
    }

    public Member updateMember(Long memberId, MemberDto updateParam) {
        return memberRepository.update(memberId, updateParam);
    }

    public void deleteMember(Long id) {
        memberRepository.delete(id);
    }

    public Member loginViaSession(HttpServletRequest request,Member member) {
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, member);
        return (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
    }

}
