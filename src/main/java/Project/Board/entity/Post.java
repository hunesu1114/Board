package Project.Board.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Post {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;
    private String author;
    private String title;
    private String content;

    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;*/


    public Post() {
    }

    public Post(String author, String title, String content) {
        this.author = author;
        this.title = title;
        this.content = content;
    }
}
