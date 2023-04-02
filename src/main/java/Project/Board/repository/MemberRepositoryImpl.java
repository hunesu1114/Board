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
/**
 * 내부호출 안하도록 조심
 * 이 클래스에는 @Transactional이 안붙어 있어서 이 클래스의 메서드에서 이 클래스의 다른 메서드 호출하면 tx적용이 안됨.
 */
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
    public List<Member> findByEmail(String memberEmail) {
        return em.createQuery("select m from Member m where m.memberEmail=:memberEmail", Member.class)
                .setParameter("memberEmail", memberEmail)
                .getResultList();   //.get(0) 하면 IndexOutOfBoundsException터짐
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }

    @Override
    public Member update(Long id, MemberDto updateParam) {
        Member member = em.find(Member.class, id);
        member.setNickName(updateParam.getNickName());
        member.setPassword(updateParam.getPassword());
        return member;
    }

    @Override
    public void delete(Long id) {
        Member member = em.find(Member.class, id);
        em.remove(member);
    }

    @Override
    public Member initSave(Member member) {
        em.persist(member);
        return member;
    }
}
