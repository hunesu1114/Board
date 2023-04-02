package Project.Board.dto;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter     //DTO에는 세터를 꼭 넣읍시다....
public class MemberDto {

    @NotBlank
    private String memberEmail;

    @NotBlank
    private String nickName;  //서비스 사용시 이용할 닉네임

    @NotBlank
    private String password;

    private LocalDateTime createTime = LocalDateTime.now();

    public MemberDto() {
    }

    public MemberDto(String memberEmail, String nickName, String password) {
        this.memberEmail = memberEmail;
        this.nickName = nickName;
        this.password = password;
    }
}
