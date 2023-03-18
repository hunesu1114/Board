package Project.Board.repository;

import Project.Board.dto.MemberDto;
import Project.Board.entity.Member;
import Project.Board.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository {

    private final EntityManager em;
    private final Mapper mapper;

    @Override
    public Member save(MemberDto dto) {
        Member member = mapper.memberDtoToEntity(dto);
        em.persist(member);
        return member;
    }

    @Override
    public Member findById(Long id) {
        Member findMember = em.find(Member.class, id);
        return findMember;
    }

    @Override
    public Member findByLoginId(String loginId) {
        return em.createQuery("select m from Member m where m.login_id=:loginId", Member.class)
                .setParameter("loginId", loginId)
                .getResultList()
                .get(0);
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }

    @Override
    public void update(Long id, MemberDto updateParam) {
        mapper.memberUpdate(findById(id), updateParam);
    }

    @Override
    public void delete(Long id) {
        Member member = em.find(Member.class, id);
        em.remove(member);
    }
}
