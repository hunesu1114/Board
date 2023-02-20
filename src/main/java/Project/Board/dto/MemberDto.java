package Project.Board.dto;

import lombok.Getter;

@Getter
public class MemberDto {

    private Long memberId;
    private String memberName;
    private String memberEmail;
    private String password;

    public MemberDto(String memberName, String memberEmail, String password) {
        this.memberName = memberName;
        this.memberEmail = memberEmail;
        this.password = password;
    }
}
