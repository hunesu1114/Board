package Project.Board.repository;

import Project.Board.dto.MemberDto;
import Project.Board.entity.Member;

import java.util.List;

public interface MemberRepository {
    public Member save(MemberDto dto);

    public Member findById(Long id);

    public Member findByEmail(String email);

    public List<Member> findAll();

    public Member update(Long id, MemberDto updateParam);

    public void delete(Long id);

    public void initSave(Member member);

}
