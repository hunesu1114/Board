package Project.Board.controller;

import Project.Board.dto.LoginDto;
import Project.Board.dto.MemberDto;
import Project.Board.entity.Member;
import Project.Board.login.session.SessionConst;
import Project.Board.login.web.Login;
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/register")
    public String register(@ModelAttribute("memberDto") MemberDto memberDto) {
        return "member/register";
    }

    @PostMapping("/register")   //데이터를 받았는데 왜 null값 나옴?
    public String register(@Validated @ModelAttribute("memberDto") MemberDto memberDto, BindingResult bindingResult,
                           HttpServletRequest request,RedirectAttributes redirectAttributes) {
        /*if (bindingResult.hasErrors()) {
            log.info("========회원가입 오류========");
            return "member/register";
        }*/

        Member registerMember = memberService.saveMember(memberDto);
        log.info("========회원가입 완료========");
        log.info("========로그인 처리 합니다========");
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, registerMember);

        session.getAttributeNames().asIterator()
                .forEachRemaining(name -> log.info("session-name={}, value={}",name,session.getAttribute(name)));

        log.info("Email={}, nickName={}, password={}", registerMember.getMemberEmail(), registerMember.getNickName(), registerMember.getPassword());
        log.info("================================");

        redirectAttributes.addAttribute("memberId", registerMember.getMemberId());
//        redirectAttributes.addAttribute("registerStatus", true);
        return "redirect:/member/individual/{memberId}";
    }

    @GetMapping("/login")
    public String login(Model model) {
        LoginDto member = new LoginDto();
        model.addAttribute("member", member);
        return "member/login";
    }

    @PostMapping("/login")
    /**
     * 로그인DTO 만들어 써야함
     * 멤버DTO로 하면 닉네임 바인딩 -> null -> 오류 생김
     * 멤버DTO쓸거면 @NotBlank 주석처리하면 되긴 함
     */
    public String login(@Validated @ModelAttribute("member") LoginDto member, BindingResult bindingResult,
                        HttpServletRequest request, @RequestParam(defaultValue = "/") String redirectURI,
                        RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "member/login";
        }

        Member loginMember = memberService.login(member.getMemberEmail(), member.getPassword());
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER,loginMember);

        if (redirectURI == "/") {
            redirectAttributes.addAttribute("memberId", loginMember.getMemberId());
            return "redirect:/member/individual/{memberId}";
        }

        return "redirect:"+redirectURI;

    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        log.info("========로그아웃 되었습니다=========");
        return "redirect:/";
    }

    @GetMapping("/individual/{memberId}")
    public String individual(@PathVariable Long memberId, Model model) {
        Member findMember = memberService.findMemberById(memberId);
        model.addAttribute("member", findMember);
        return "member/individual";
    }

    @GetMapping("/individual/{memberId}/edit")
    public String edit(@PathVariable Long memberId, Model model) {
        Member member = memberService.findMemberById(memberId);
        model.addAttribute("member", member);
        return "member/edit";
    }

    @PostMapping("/individual/{memberId}/edit")
    public String edit(@PathVariable Long memberId, @Validated @ModelAttribute("member") MemberDto updateParam,
                       BindingResult bindingResult,RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            log.info("수정페이지 오류발생 : {}", bindingResult);
        }
        Member member = memberService.updateMember(memberId, updateParam);
        redirectAttributes.addAttribute("memberId", member.getMemberId());

        return "redirect:/member/individual/{memberId}";
    }
}
