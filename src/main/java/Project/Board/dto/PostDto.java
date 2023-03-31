package Project.Board.dto;

import lombok.Getter;
import lombok.Setter;
import net.bytebuddy.asm.Advice;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
public class PostDto {

    @NotBlank
    private String nickName;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotBlank
    private LocalDateTime createTime = LocalDateTime.now();

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
