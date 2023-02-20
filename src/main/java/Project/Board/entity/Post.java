package Project.Board.entity;

import lombok.Getter;

import javax.persistence.Entity;

@Entity
@Getter
public class Post {

    private Long postId;
    private String title;
    private String content;

}
