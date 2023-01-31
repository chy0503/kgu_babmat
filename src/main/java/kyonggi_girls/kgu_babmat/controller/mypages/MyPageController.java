package kyonggi_girls.kgu_babmat.controller.mypages;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kyonggi_girls.kgu_babmat.dto.Like;
import kyonggi_girls.kgu_babmat.dto.User;
import kyonggi_girls.kgu_babmat.service.StoreService;
import kyonggi_girls.kgu_babmat.session.SessionConst;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Controller
public class MyPageController {
    private final StoreService storeService;

    public MyPageController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping("myPage")
    public String myPage() {
        return "mypages/myPage";
    }

    @GetMapping("likedMenu")
    public String likedMenu(Model model, HttpServletRequest request) throws ExecutionException, InterruptedException {
        // session
        HttpSession session = request.getSession(false); // 세션 받기
        if (session == null) // 세션이 없으면 intro 화면으로 return
            return "redirect:/";
        User user = (User) session.getAttribute(SessionConst.sessionId); // 세션에 있는 user 정보 받기
        model.addAttribute("user", user); // 화면에 전달하기 위해 model에 add

        List<Like> likeMenuList = storeService.showLike_all(user.getEmail());
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
        storeService.updateLike(user.getEmail(), like.getMenu(), like.getPrice(), like.getSelectStore(), like.getStore());
        System.out.println("좋아요 모아보기 : "+storeService.showLike_all(user.getEmail())); // 지우면 새로고침 해야지 반영됨
        return "redirect:/likedMenu";
    }
}
