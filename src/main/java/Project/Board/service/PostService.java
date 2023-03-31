package Project.Board.service;

import Project.Board.dto.MemberDto;
import Project.Board.dto.PostDto;
import Project.Board.entity.Member;
import Project.Board.entity.Post;
import Project.Board.login.session.SessionConst;
import Project.Board.pagination.Pagination;
import Project.Board.repository.JpaPostRepository;
import Project.Board.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final JpaPostRepository jpaRepository;

    public Post savePost(PostDto dto,HttpServletRequest request) {
        return postRepository.save(dto,request);
    }

    public Post findPostById(Long postId) {
        return postRepository.findById(postId);
    }

//    public List<Post> findAllPost(int page) {
//        return postRepository.pagedFindAll(page);
//    }

    public List<Post> findAllByPage(Pageable pageable) {
        return jpaRepository.findAll(pageable).getContent();
    }

    public List<Post> searchPost(String keyword) {
        List<Post> rs1 = jpaRepository.findByContentContaining(keyword);
        List<Post> rs2 = jpaRepository.findByTitleContaining(keyword);
        List<Post> rs = new ArrayList<>();
        rs.addAll(rs1);
        rs.addAll(rs2);
        return rs;
    }
    public int postCnt() {
        return postRepository.postCnt();
    }

    public void updatePost(Long postId, PostDto updateParam) {
        postRepository.update(postId, updateParam);
    }

    public void deletePost(Long postId) {
        postRepository.delete(postId);
    }

    public Boolean isAuthor(Post post, HttpServletRequest request) {
        return (post.getMember().getMemberId()==getMemberFromSession(request).getMemberId());
    }

    public Member getMemberFromSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
    }

}
