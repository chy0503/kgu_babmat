package kyonggi_girls.kgu_babmat.controller.mypages;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kyonggi_girls.kgu_babmat.dao.LikeDao;
import kyonggi_girls.kgu_babmat.dao.ReviewDao;
import kyonggi_girls.kgu_babmat.dao.UserDao;
import kyonggi_girls.kgu_babmat.dto.Like;
import kyonggi_girls.kgu_babmat.dto.StoreReview;
import kyonggi_girls.kgu_babmat.dto.User;
import kyonggi_girls.kgu_babmat.session.SessionConst;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Controller
public class MyPageController {
    private final ReviewDao reviewDao;
    private final UserDao userDao;
    private final LikeDao likeDao;

    public MyPageController(ReviewDao reviewDao, UserDao userDao, LikeDao likeDao) {
        this.reviewDao = reviewDao;
        this.userDao = userDao;
        this.likeDao = likeDao;
    }

    @GetMapping("myReview")
    public String myReview(Model model, HttpServletRequest request) throws ExecutionException, InterruptedException {
        // session
        HttpSession session = request.getSession(false);
        if (session == null)
            return "redirect:/";
        User user = (User) session.getAttribute(SessionConst.sessionId);
        model.addAttribute("user", user);
        List<StoreReview> reviewList = reviewDao.showReview_all(user.getEmail());
        model.addAttribute("reviewList", reviewList);

        return "mypages/myReview";
    }

    @GetMapping("likedMenu")
    public String likedMenu(Model model, HttpServletRequest request) throws ExecutionException, InterruptedException {
        // session
        HttpSession session = request.getSession(false); // 세션 받기
        if (session == null) // 세션이 없으면 intro 화면으로 return
            return "redirect:/";
        User user = (User) session.getAttribute(SessionConst.sessionId); // 세션에 있는 user 정보 받기
        model.addAttribute("user", user); // 화면에 전달하기 위해 model에 add

        List<Like> likeMenuList = likeDao.showLike_all(user.getEmail());
        model.addAttribute("likeMenuList", likeMenuList);
        return "mypages/likedMenu";
    }

    @PostMapping("likedMenu")
    public String likedMenu(@ModelAttribute Like like, HttpServletRequest request) throws Exception {
        // session
        HttpSession session = request.getSession(false);
        if (session == null)
            return "redirect:/";
        User user = (User) session.getAttribute(SessionConst.sessionId);
        likeDao.updateLike(user.getEmail(), like.getMenu(), like.getPrice(), like.getSelectStore(), like.getStore());
        System.out.println("좋아요 모아보기 : " + likeDao.showLike_all(user.getEmail())); // 지우면 새로고침 해야지 반영됨
        return "redirect:/likedMenu";
    }

    @GetMapping("/myInfo")
    public String UserInfo(Model model, HttpServletRequest request) throws ExecutionException, InterruptedException {
        // session
        HttpSession session = request.getSession(false);
        if (session == null)
            return "redirect:/";
        User userTmp = (User) session.getAttribute(SessionConst.sessionId);
        User user = userDao.getUser(userTmp.getEmail());
        model.addAttribute("user", user);

        User users = userDao.getUserInfo(user.getEmail());
        model.addAttribute("users", users);
        model.addAttribute("username", user.getUsername());

        return "mypages/myInfo";
    }

    @PostMapping("/editedUser")
    public String editUser(@ModelAttribute User users, HttpServletRequest request, RedirectAttributes redirect) throws Exception {
        // session
        HttpSession session = request.getSession(false);
        if (session == null)
            return "redirect:/";

        userDao.updateUser(users.getEmail(), users.getUsername());
        return "redirect:/main";
    }

    @PostMapping("/deleteUser")
    public String deleteUser(@RequestParam("email") String email, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "redirect:/";
        }
        session.invalidate();

        userDao.deleteUser(email);
        return "redirect:/";
    }
}
