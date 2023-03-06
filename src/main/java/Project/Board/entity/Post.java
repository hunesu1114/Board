package Project.Board.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Post {

    @Id @GeneratedValue
    private Long postId;
    private String title;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    public Post() {
    }

    public Post(Member member, String title, String content) {
        this.title = title;
        this.content = content;
        this.member = member;
    }
}
