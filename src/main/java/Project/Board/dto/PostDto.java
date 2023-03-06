package Project.Board.dto;

import Project.Board.entity.Member;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class PostDto {

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotBlank
    private Member member;

    public PostDto(Member member, String title, String content) {
        this.title = title;
        this.content = content;
        this.member = member;
    }
}
