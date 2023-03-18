package Project.Board.repository;

import Project.Board.dto.MemberDto;
import Project.Board.entity.Member;

import java.util.List;

public interface MemberRepository {
    public Member save(MemberDto dto);

    public Member findById(Long id);

    public Member findByLoginId(String LoginId);

    public List<Member> findAll();

    public void update(Long id, MemberDto updateParam);

    public void delete(Long id);

}
