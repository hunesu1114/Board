package Project.Board.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="member_id")
    private Long memberId;
    private String memberEmail;
    private String nickName;  //서비스 사용시 이용할 닉네임
    private String password;
    private LocalDateTime createTime;

    @OneToMany(mappedBy = "member")
    private List<Post> posts=new ArrayList<>();

    public Member() {
    }

    public Member(String memberEmail, String nickName, String password) {
        this.memberEmail = memberEmail;
        this.nickName = nickName;
        this.password = password;
    }
}
