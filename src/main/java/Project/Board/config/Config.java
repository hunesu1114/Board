package Project.Board.config;

import Project.Board.dto.PostDto;
import Project.Board.entity.Member;
import Project.Board.service.PostService;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class Config {

    private final PostService postService;

    public Config(PostService postService) {
        this.postService = postService;
    }

    @PostConstruct
    public void init() {
        for (int i = 0; i < 53; i++) {
            Member member = new Member("member" + i, "email" + i, "pw" + i, null);
            PostDto post = new PostDto(member, "제목" + i, "내용" + i);
            postService.savePost(post);
        }
    }
}
