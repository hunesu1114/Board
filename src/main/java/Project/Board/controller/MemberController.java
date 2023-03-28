package Project.Board.controller;

import Project.Board.dto.LoginDto;
import Project.Board.dto.MemberDto;
import Project.Board.entity.Member;
import Project.Board.login.session.SessionConst;
import Project.Board.mapper.Mapper;
import Project.Board.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;
    private final Mapper mapper;

    @GetMapping("/register")
    public String register(Model model) {
        MemberDto member = new MemberDto();
        model.addAttribute("member", member);
        return "member/register";
    }

    @PostMapping("/register")
    public String register(@Validated @ModelAttribute("member") MemberDto member, BindingResult bindingResult,
                           HttpServletRequest request, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            log.info("========회원가입 오류========");
            return "member/register";
        }

        Member registerMember = memberService.saveMember(member);
//        redirectAttributes.addAttribute("registerStatus", true);
        log.info("========회원가입 완료========");

        log.info("========로그인 처리 합니다========");
        memberService.loginViaSession(request, registerMember);
//        redirectAttributes.addAttribute("loginStatus", true);
        log.info("========로그인 성공=========");

        redirectAttributes.addAttribute("memberId", registerMember.getMemberId());
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
    public String login(@Validated @ModelAttribute("member") LoginDto loginMemberDto, BindingResult bindingResult,
                        HttpServletRequest request, @RequestParam(defaultValue = "/") String redirectURI,
                        RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "member/login";
        }

        Member loginMember = memberService.loginValidation(loginMemberDto.getMemberEmail(), loginMemberDto.getPassword());
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);

        log.info("========로그인 성공=========");

        if (redirectURI == "/") {
            redirectAttributes.addAttribute("memberId", loginMember.getMemberId());
            redirectAttributes.addAttribute("loginStatus", true);

            return "redirect:/member/individual/{memberId}";
        }

        return "redirect:" + redirectURI;

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
        MemberDto memberDto = mapper.memberEntityToDto(member);
        model.addAttribute("member", memberDto);
        return "member/edit";
    }

    @PostMapping("/individual/{memberId}/edit")
    public String edit(@PathVariable Long memberId, @Validated @ModelAttribute("member") MemberDto updateParam,
                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.info("수정페이지 오류발생 : {}", bindingResult);
        }
        memberService.updateMember(memberId, updateParam);

        return "redirect:/member/individual/{memberId}";
    }

    @GetMapping("/individual/{memberId}/delete")
    public String delete(@PathVariable Long memberId,HttpServletRequest request) {
        memberService.deleteMember(memberId);
        request.getSession().invalidate();
        return "member/delete";
    }
}
