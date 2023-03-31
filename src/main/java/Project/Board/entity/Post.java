package Project.Board.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Post {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;
    private String title;
    private String content;
    private LocalDateTime createTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    public Post() {
    }

    public Post(String title, String content, LocalDateTime createTime, Member member) {
        this.title = title;
        this.content = content;
        this.createTime = createTime;
        this.member = member;
    }
}
