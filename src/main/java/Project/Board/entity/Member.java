package Project.Board.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="member_id")
    private Long memberId;
    private String memberName;  //서비스 사용시 이용할 닉네임
    private String memberEmail;
    private String password;

    /*@OneToMany(mappedBy = "member")
    private List<Post> posts=new ArrayList<>();*/

    public Member() {
    }

    public Member(String memberName, String memberEmail, String password) {
        this.memberName = memberName;
        this.memberEmail = memberEmail;
        this.password = password;
    }
}
