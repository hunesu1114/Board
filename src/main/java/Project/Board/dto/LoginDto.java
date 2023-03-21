package Project.Board.dto;

import javax.validation.constraints.NotBlank;

public class LoginDto {

    @NotBlank
    private String memberEmail;

    @NotBlank
    private String password;

    public LoginDto() {
    }

    public LoginDto(String memberEmail, String password) {
        this.memberEmail = memberEmail;
        this.password = password;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
