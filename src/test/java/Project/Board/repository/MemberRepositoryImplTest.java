package Project.Board.repository;

import Project.Board.dto.MemberDto;
import Project.Board.entity.Member;
import Project.Board.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.RequiredTypes;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberRepositoryImplTest {

    @Autowired
    private MemberRepository memberRepository;
    //JUnit5 에서는 @Autowired를 통한 DI를 해야 오류가 안남 (@RequiredArguementConstruct 쓰니까 오류났음)

    void MemberRepositoryImplTest(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Test
    void saveTest() {
        MemberDto dto = new MemberDto("email", "nick", "pw");
        Member savedMember = memberRepository.save(dto);
        Assertions.assertThat(savedMember.getMemberEmail()).isEqualTo(dto.getMemberEmail());
        Assertions.assertThat(savedMember.getNickName()).isEqualTo(dto.getNickName());
        Assertions.assertThat(savedMember.getPassword()).isEqualTo(dto.getPassword());
    }
}