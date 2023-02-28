package Project.Board.entity;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Member {

    @Id @GeneratedValue
    @Column(name="member_id")
    private Long memberId;
    private String memberName;
    private String memberEmail;
    private String password;

    @OneToMany(mappedBy = "member")
    private List<Post> posts=new ArrayList<>();


}
