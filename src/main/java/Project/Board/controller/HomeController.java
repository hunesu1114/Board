package Project.Board.controller;

import Project.Board.entity.Member;
import Project.Board.login.session.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j
public class HomeController {
    @GetMapping("/")
    public String home(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        if (session.getAttribute(SessionConst.LOGIN_MEMBER) != null) {
            Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
            model.addAttribute("member", member);
            return "index_login";
        }
        return "index";
    }

}
