package Project.Board.entity;

import lombok.Getter;

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
