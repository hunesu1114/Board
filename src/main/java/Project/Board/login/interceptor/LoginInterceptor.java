package Project.Board.login.interceptor;

import Project.Board.login.session.SessionConst;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        String requestURI = request.getRequestURI();
        if (session == null||session.getAttribute(SessionConst.LOGIN_MEMBER) == null) {
            response.sendRedirect("/member/login?redirectURI=" + requestURI);
            return false;
        }
        return true;
    }
}
