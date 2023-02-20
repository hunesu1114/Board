package Project.Board.dto;

import lombok.Getter;

@Getter
public class PostDto {

    private Long postId;
    private String title;
    private String content;

    public PostDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
