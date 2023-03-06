package Project.Board.service;

import Project.Board.dto.MemberDto;
import Project.Board.entity.Member;
import Project.Board.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public void updateMember(Long memberId, MemberDto updateParam) {
        memberRepository.update(memberId, updateParam);
    }

    public void deleteMember(Long memberId) {
        memberRepository.delete(memberId);
    }
}
