package kyonggi_girls.kgu_babmat.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kyonggi_girls.kgu_babmat.dao.UserDao;
import kyonggi_girls.kgu_babmat.dto.User;
import kyonggi_girls.kgu_babmat.service.LoginService;
import kyonggi_girls.kgu_babmat.session.SessionConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;


@Controller
public class SignupController {

    @Autowired
    private final UserDao userDao;

    private final LoginService loginService;

    public SignupController(UserDao userDao, LoginService loginService) {
        this.userDao = userDao;
        this.loginService = loginService;
    }

    @GetMapping("/signup")
    public String signUp(HttpServletRequest request, Model model) {

        HttpSession session = request.getSession(false);

        if (session == null) {
            return "signin";
        }

        User user = (User) session.getAttribute(SessionConst.sessionId);
        model.addAttribute("user", user);

        return "signup";
    }

    @PostMapping("/signup")
    public String signUpProc(Model model, @RequestParam Map<String, String> params, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        System.out.println(params);


        String email = params.get("email");
        //String userPwd = params.get("user-password");
        String username = params.get("name");
        //String userFood = params.get("user-food");


        // back-end validation
        String results = "";
        boolean isAllValidate = true;
  /*      if(username.length() < 0) {
            results += "user-id must be at least 4 character long.\n";
            isAllValidate = false;
        }

        if(email.length() <= 4) {
            results += "user-name is not entered.\n";
            isAllValidate = false;
        }*/

/*    if (loginService.isUser(email)) {
            session.setAttribute(SessionConst.sessionId, loginService.isUser(email));
            return "redirect:/main";
        }
        return "signup";*/

        // submit to database
        if (isAllValidate) {
            session.setAttribute(SessionConst.sessionId, loginService.isUser(email));

            try {
                int insertResult = userDao.insertUser(email, username);
                if (insertResult >= 1) {
                    results += "You have successfully registered.";
                } else {
                    results += "Sign-up failed. There may be duplicate information or other issues.";
                    isAllValidate = false;
                }
            } catch (Exception e) {
                e.printStackTrace();
                results += "Sign-up failed. There may be duplicate information or other issues.";
                isAllValidate = false;
            }
            return "main";

        }

        model.addAttribute("isSuccess", isAllValidate);
        model.addAttribute("resultMsg", results);

        return "redirect:/main";
    }
}

