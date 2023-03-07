package Project.Board.dto;

import Project.Board.entity.Member;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class PostDto {

    @NotBlank
    private String author;

    @NotBlank
    private String title;

    @NotBlank
    private String content;


    public PostDto(String author, String title, String content) {
        this.author = author;
        this.title = title;
        this.content = content;
    }
}
