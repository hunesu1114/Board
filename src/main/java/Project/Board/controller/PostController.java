package Project.Board.controller;

import Project.Board.dto.PostDto;
import Project.Board.entity.Member;
import Project.Board.entity.Post;
import Project.Board.login.session.SessionConst;
import Project.Board.pagination.Pagination;
import Project.Board.pagination.PagingConst;
import Project.Board.service.PostService;
import com.sun.net.httpserver.HttpsServer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/main/{page}")
    public String mainPage(@PathVariable int page, Model model) {
        Pageable pageable = PageRequest.of(page - 1, PagingConst.POST_CNT_PER_PAGE, Sort.by("postId").descending());
        List<Post> posts = postService.findAllByPage(pageable);

        Pagination pagination = new Pagination(postService.postCnt(), page);

        model.addAttribute("pagination", pagination);
        model.addAttribute("pagesInCurrentBlock", pagination.pagesInCurrentBlock());
        model.addAttribute("posts", posts);
        model.addAttribute("keyword", new String());
        return "/board/main";
    }

    @PostMapping("/main/{page}")
    public String mainPage(@ModelAttribute("keyword") String keyword, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("search", keyword);
        return "redirect:/board/searchMain";
    }

    @GetMapping("/searchMain")
    public String searchMain(@RequestParam("search") String keyword, Model model) {
        List<Post> rs = postService.searchPost(keyword);

        model.addAttribute("posts", rs);
        model.addAttribute("keyword", new String());
        return "board/searchMain";
    }

    @PostMapping("/searchMain")
    public String searchMain(@ModelAttribute("keyword") String keyword, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("search", keyword);
        return "redirect:/board/searchMain";
    }

    @GetMapping("/{postId}")
    public String post(@PathVariable Long postId, HttpServletRequest request, Model model) {
        Post findPost = postService.findPostById(postId);
        Boolean isAuthor = postService.isAuthor(findPost, request);
        model.addAttribute("isAuthor", isAuthor);
        model.addAttribute("post", findPost);
        return "board/post";
    }

    @GetMapping("/addPost")
    public String addPost(HttpServletRequest request, Model model) {
        PostDto post = new PostDto(postService.getMemberFromSession(request).getNickName());

        model.addAttribute("post", post);
        return "board/add";
    }

    @PostMapping("/addPost")
    public String addPost(@ModelAttribute("post") PostDto dto, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        Post post = postService.savePost(dto, request);

        redirectAttributes.addAttribute("postId", post.getPostId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/board/{postId}";
    }

    @GetMapping("/{postId}/edit")
    public String edit(@PathVariable Long postId, Model model) {
        Post findPost = postService.findPostById(postId);
        PostDto dto = new PostDto(findPost.getMember().getNickName(), findPost.getTitle(), findPost.getContent());
        model.addAttribute("post", dto);
        model.addAttribute("postId", postId);
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

    @GetMapping("/{postId}/delete")
    public String delete(@PathVariable Long postId) {
        postService.deletePost(postId);
        return "board/delete";
    }
}
