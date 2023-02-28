package Project.Board.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Member {

    @Id @GeneratedValue
    @Column(name="member_id")
    private Long memberId;
    private String memberName;
    private String memberEmail;
    private String password;

    //@OneToMany(mappedBy = "member")
    private Long post;


}
