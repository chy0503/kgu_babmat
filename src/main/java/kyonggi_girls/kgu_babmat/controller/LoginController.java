package kyonggi_girls.kgu_babmat.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kyonggi_girls.kgu_babmat.dao.UserDao;
import kyonggi_girls.kgu_babmat.session.SessionConst;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.concurrent.ExecutionException;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UserDao userDao;

    @GetMapping("/")
    public String intro(){
        return "intro";
    }

    @GetMapping("signup")
    public String getUsername(@AuthenticationPrincipal OAuth2User principal, HttpServletRequest request, Model model) throws ExecutionException, InterruptedException {
        String email = principal.getAttribute("email");
        String username = principal.getAttribute("name");

        // 유저 정보가 있을 경우 : 로그인 성공
        if (userDao.isUserExit(email)) {
            HttpSession session = request.getSession();
            session.setAttribute(SessionConst.sessionId, userDao.getUser(email));
            return "redirect:/main";
        }

        // 유저 정보가 없을 경우 - 이메일이 kyonggi.ac.kr이 아닐 경우
        String[] split = email.split("@");
        if (!split[split.length - 1].equals("kyonggi.ac.kr")) {
            return "redirect:/alert_rejectSignup";
        }

        model.addAttribute("email", email);
        model.addAttribute("username", username);
        return "signup";
    }

    @GetMapping("/alert_rejectSignup")
    public String alert (HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            return "redirect:/main";
        }
        return "alert_rejectSignup";
    }

    @PostMapping("/signup")
    public String updateUsername(@RequestParam("email") String email, @RequestParam("username") String username) throws ExecutionException, InterruptedException {
        userDao.createUser(email, username);
        return "redirect:/";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "redirect:/";
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        session.invalidate();
        return "redirect:/";
    }
}