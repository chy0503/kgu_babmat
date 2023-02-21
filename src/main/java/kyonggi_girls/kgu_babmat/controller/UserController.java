package kyonggi_girls.kgu_babmat.controller;

import kyonggi_girls.kgu_babmat.dto.User;
import kyonggi_girls.kgu_babmat.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("users")
    public String gamcoMenu(Model model) throws Exception {
        List<User> userList = userService.getUsers();
        model.addAttribute("userList", userList);
        return "users";
    }

}
