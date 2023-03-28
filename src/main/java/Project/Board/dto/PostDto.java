package Project.Board.dto;

import Project.Board.entity.Member;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class PostDto {

    @NotBlank
    private String nickName;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    public PostDto() {
    }

    public PostDto(String nickname) {
        this.nickName = nickname;
    }

    public PostDto(String nickname, String title, String content) {
        this.nickName = nickname;
        this.title = title;
        this.content = content;
    }
}
