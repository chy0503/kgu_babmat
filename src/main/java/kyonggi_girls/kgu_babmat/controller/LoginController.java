package kyonggi_girls.kgu_babmat.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kyonggi_girls.kgu_babmat.dto.User;
import kyonggi_girls.kgu_babmat.service.LoginService;
import kyonggi_girls.kgu_babmat.session.SessionConst;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.concurrent.ExecutionException;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;


    @GetMapping("login")
    public String home(HttpServletRequest request, Model model) throws ExecutionException, InterruptedException {
        HttpSession session = request.getSession(false);

        if (session == null) {
            return "login";
        }

        User user = (User) session.getAttribute(SessionConst.sessionId);
        model.addAttribute("user", user);

        return "redirect:/main";
    }


    @PostMapping("/login")
    public String login(@RequestParam String email, HttpServletRequest request) throws ExecutionException, InterruptedException {
        HttpSession session = request.getSession();
        if (loginService.isUser(email)) {
            session.setAttribute(SessionConst.sessionId, loginService.getUser(email));
            return "redirect:/main";
        }
        return "login";
    }


    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "redirect:/";
        }
        session.invalidate();
        return "redirect:/";
    }

}

