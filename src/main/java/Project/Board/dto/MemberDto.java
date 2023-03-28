package Project.Board.dto;
import lombok.Getter;
import javax.validation.constraints.NotBlank;

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

    public MemberDto(String nickName, String memberEmail, String password) {
        this.nickName = nickName;
        this.memberEmail = memberEmail;
        this.password = password;
    }
}
