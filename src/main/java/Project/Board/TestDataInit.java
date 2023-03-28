package Project.Board;

import Project.Board.dto.MemberDto;
import Project.Board.dto.PostDto;
import Project.Board.entity.Member;
import Project.Board.entity.Post;
import Project.Board.repository.MemberRepository;
import Project.Board.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Slf4j
@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    @EventListener(ApplicationReadyEvent.class)     //@PostConstruct 대신 사용해주어야 함
    // AOP 같은부분(@Transactional)이 아직 다처리되지않은 시점에호출될수 있기때문에, 간혹문제가 발생할수 있다.
    @Transactional      //와 이걸 안해줘서 3시간 허비함....
    public void init() {
        log.info("실행=====================================================");
        for (int i = 1; i < 54; i++) {
            Member member = new Member("email" + i, "nick" + i, "pw" + i);
            memberRepository.initSave(member);
            Post post = new Post("제목"+i, "내용" + i, member);
            postRepository.initSave(post);
        }
    }
}
