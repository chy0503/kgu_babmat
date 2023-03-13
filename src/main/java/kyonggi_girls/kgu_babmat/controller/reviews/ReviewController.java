package kyonggi_girls.kgu_babmat.controller.reviews;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kyonggi_girls.kgu_babmat.dto.StoreReview;
import kyonggi_girls.kgu_babmat.dto.User;
import kyonggi_girls.kgu_babmat.service.LoginService;
import kyonggi_girls.kgu_babmat.service.ReviewService;
import kyonggi_girls.kgu_babmat.session.SessionConst;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.*;
import java.util.concurrent.ExecutionException;


@Controller
public class ReviewController {

    private final ReviewService reviewService;
    private final LoginService loginService;


    public ReviewController(ReviewService reviewService, LoginService loginService) {
        this.reviewService = reviewService;
        this.loginService = loginService;
    }

    @GetMapping("review")
    public String myReview(@ModelAttribute StoreReview storeReview, Model model, HttpServletRequest request, @RequestParam String storeName) throws ExecutionException, InterruptedException {
        // session
        HttpSession session = request.getSession(false);
        if (session == null)
            return "redirect:/";

        User user = (User) session.getAttribute(SessionConst.sessionId);
        model.addAttribute("user", user);

        List<StoreReview> storeReviews = reviewService.showReview_all_store(storeName);
        for (StoreReview sr : storeReviews) {
            sr.setUsername(loginService.getUser(sr.getEmail()).getUsername());
        }
        model.addAttribute("storeReviews", storeReviews);
        return "reviews/review";
    }
}

