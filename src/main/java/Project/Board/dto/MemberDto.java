package Project.Board.dto;

import Project.Board.entity.Post;
import lombok.Getter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Getter
public class MemberDto {


    private Long memberId;
    private String memberName;  //서비스 사용시 이용할 닉네임
    private String memberEmail;
    private String password;
    private List<Post> posts;

    public MemberDto(String memberName, String memberEmail, String password,List<Post> posts) {
        this.memberName = memberName;
        this.memberEmail = memberEmail;
        this.password = password;
        this.posts = posts;
    }
}
