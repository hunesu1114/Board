package Project.Board.controller;

import Project.Board.dto.PostDto;
import Project.Board.entity.Member;
import Project.Board.entity.Post;
import Project.Board.pagination.Pagination;
import Project.Board.pagination.PagingConst;
import Project.Board.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final PagingConst pConstant;

    @GetMapping("/main/{page}")
    public String mainPage(@PathVariable int page,Model model) {
        Pagination pagination = new Pagination(postService.postCnt(), page, pConstant.getPOST_CNT_PER_PAGE(), pConstant.getPAGE_CNT_PER_BLOCK());
        List<Post> posts = postService.findAllPost(pagination);
        model.addAttribute("pagination", pagination);
        model.addAttribute("pagesInCurrentBlock", pagination.pagesInCurrentBlock());
        model.addAttribute("posts", posts);
        return "board/main/{page}";
    }

    @GetMapping("/{postId}")
    public String post(@PathVariable Long postId, Model model) {
        Post findPost = postService.findPostById(postId);
        model.addAttribute("post", findPost);
        return "board/post";
    }

    @GetMapping("/addPost")
    public String addPost(Model model) {
        Post post = new Post();
        model.addAttribute("post", post);
        return "board/add";
    }

    @PostMapping("/addPost")
    public String addPost(@ModelAttribute("post") PostDto dto, RedirectAttributes redirectAttributes) {
        Post post = postService.savePost(dto);
        redirectAttributes.addAttribute("postId", post.getPostId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/board/{postId}";
    }

    @GetMapping("/{postId}/edit")
    public String edit(@PathVariable Long postId, Model model) {
        Post findPost = postService.findPostById(postId);
        model.addAttribute("post", findPost);
        return "board/edit";
    }

    @PostMapping("/{postId}/edit")
    public String edit(@PathVariable Long postId, @Validated @ModelAttribute("post") PostDto updateParam, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            log.info("수정페이지 오류발생 : {}", bindingResult);
            return "board/edit";
        }
        postService.updatePost(postId, updateParam);
        return "redirect:/board/{postId}";
    }

    //삭제 메서드 구현해야함


}
