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
//    private LocalDateTime localDateTime;  //작성일자 추가하기

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    public Post() {
    }

    public Post( String title, String content, Member member) {
        this.title = title;
        this.content = content;
        this.member = member;
    }
}
