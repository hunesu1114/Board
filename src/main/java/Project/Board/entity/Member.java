package Project.Board.entity;

import lombok.Getter;

import javax.persistence.Entity;

@Entity
@Getter
public class Member {

    private Long memberId;
    private String memberName;
    private String memberEmail;
    private String password;

    public Member() {
    }

    public Member(String memberName, String memberEmail, String password) {
        this.memberName = memberName;
        this.memberEmail = memberEmail;
        this.password = password;
    }
}
