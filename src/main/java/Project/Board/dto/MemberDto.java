package Project.Board.dto;

import Project.Board.entity.Post;
import lombok.Getter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Getter
public class MemberDto {

    @NotBlank
    private String nickName;  //서비스 사용시 이용할 닉네임

    @NotBlank
    private String memberEmail;

    @NotBlank
    private String password;

    public MemberDto() {
    }

    public MemberDto(String memberName, String memberEmail, String password) {
        this.nickName = memberName;
        this.memberEmail = memberEmail;
        this.password = password;
    }
}
