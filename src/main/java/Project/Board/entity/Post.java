package Project.Board.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Post {

    @Id @GeneratedValue
    private Long postId;
    private String title;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

}
