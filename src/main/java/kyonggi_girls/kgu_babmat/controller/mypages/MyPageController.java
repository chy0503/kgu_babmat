package kyonggi_girls.kgu_babmat.controller.mypages;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kyonggi_girls.kgu_babmat.dto.Like;
import kyonggi_girls.kgu_babmat.dto.StoreReview;
import kyonggi_girls.kgu_babmat.dto.User;
import kyonggi_girls.kgu_babmat.service.ReviewService;
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
    private final ReviewService reviewService;

    public MyPageController(StoreService storeService, ReviewService reviewService) {
        this.storeService = storeService;
        this.reviewService = reviewService;
    }

    @GetMapping("myPage")
    public String myReview(Model model, HttpServletRequest request) throws ExecutionException, InterruptedException {
        // session
        HttpSession session = request.getSession(false);
        if (session == null)
            return "redirect:/";
        User user = (User) session.getAttribute(SessionConst.sessionId);
        model.addAttribute("user", user);

        List<StoreReview> reviewList = reviewService.showReview_all(user.getEmail());
        model.addAttribute("reviewList", reviewList);
        return "mypages/myPage";
    }
    @PostMapping("myPage")
    public String reviewCollect(@ModelAttribute StoreReview storeReview, HttpServletRequest request) throws Exception {
        // session
        HttpSession session = request.getSession(false);
        if (session == null)
            return "redirect:/";
        User user = (User) session.getAttribute(SessionConst.sessionId);
        reviewService.updateReview(user.getEmail(), storeReview.getMenu(), storeReview.getReview(),  storeReview.getReviewScore());
        System.out.println("?????? ???????????? : "+reviewService.showReview_all(user.getEmail()));
        return "redirect:/myPage";
    }

    @GetMapping("likedMenu")
    public String likedMenu(Model model, HttpServletRequest request) throws ExecutionException, InterruptedException {
        // session
        HttpSession session = request.getSession(false); // ?????? ??????
        if (session == null) // ????????? ????????? intro ???????????? return
            return "redirect:/";
        User user = (User) session.getAttribute(SessionConst.sessionId); // ????????? ?????? user ?????? ??????
        model.addAttribute("user", user); // ????????? ???????????? ?????? model??? add

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
        System.out.println("????????? ???????????? : "+storeService.showLike_all(user.getEmail())); // ????????? ???????????? ????????? ?????????
        return "redirect:/likedMenu";
    }
}
