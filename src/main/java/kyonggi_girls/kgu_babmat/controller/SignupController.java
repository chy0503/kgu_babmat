package kyonggi_girls.kgu_babmat.controller;

import kyonggi_girls.kgu_babmat.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.concurrent.ExecutionException;

@Controller
@RequiredArgsConstructor
public class SignupController {

    private final UserService userService;
    @GetMapping("signup")
    public String getUsername(@AuthenticationPrincipal OAuth2User principal,  Model model) throws ExecutionException, InterruptedException {
        String email = principal.getAttribute("email");
        String username = principal.getAttribute("name");
        model.addAttribute("email", email);
        model.addAttribute("username", username);
        return "signup";
    }

    @PostMapping("/signup")
    public String updateUsername(@RequestParam("email") String email, @RequestParam("username") String username) throws ExecutionException, InterruptedException {
        userService.updateUser(email, username);
        return "redirect:/";
    }
}

