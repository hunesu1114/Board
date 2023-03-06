package Project.Board.dto;

import Project.Board.entity.Member;
import lombok.Getter;

@Getter
public class PostDto {

    private Long postId;
    private String title;
    private String content;
    private Member member;

    public PostDto(Member member, String title, String content) {
        this.title = title;
        this.content = content;
        this.member = member;
    }
}
