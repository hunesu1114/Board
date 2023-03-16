package Project.Board.controller;

import Project.Board.dto.MemberDto;
import Project.Board.entity.Member;
import Project.Board.service.MemberService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.security.PermitAll;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/register")
    public String register(Model model) {
        MemberDto member = new MemberDto();
        model.addAttribute("member", member);
        return "member/register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("member") MemberDto dto, RedirectAttributes redirectAttributes) {
        Member member = memberService.saveMember(dto);
        redirectAttributes.addAttribute("memberId", member.getMemberId());
        return "redirect:/member/individual/{memberId}";
    }

    @GetMapping("/login")
    public String login(Model model) {
        Member member = new Member();
        model.addAttribute("member", member);
        return "/member/login";
    }

    @PostMapping("/login")
    public String login(@Validated @ModelAttribute("member") MemberDto memberDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "member/login";
        }
        //로그인 로직 구현 (세션)

        return "/member/individual/{memberId}";
    }

    @GetMapping("/individual/{memberId}")
    public String individual(@PathVariable Long memberId, Model model) {
        Member findMember = memberService.findMemberById(memberId);
        model.addAttribute("member", findMember);
        return "member/individual";
    }

    @GetMapping("/individual/{memberId}/edit")
    public String edit(@PathVariable Long memberId, Model model) {
        Member findMember = memberService.findMemberById(memberId);
        model.addAttribute("member", memberId);
        return "member/edit";
    }

    @PostMapping("/individual/{memberId}/edit")
    public String edit(@PathVariable Long memberId, @Validated @ModelAttribute("member") MemberDto updateParam, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.info("수정페이지 오류발생 : {}", bindingResult);
        }
        memberService.updateMember(memberId, updateParam);
        return "/member/individual/{memberId}";
    }
}
