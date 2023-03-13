package kyonggi_girls.kgu_babmat.controller.reviews;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kyonggi_girls.kgu_babmat.dto.Menu;
import kyonggi_girls.kgu_babmat.dto.Store;
import kyonggi_girls.kgu_babmat.dto.StoreReview;
import kyonggi_girls.kgu_babmat.dto.User;
import kyonggi_girls.kgu_babmat.service.LoginService;
import kyonggi_girls.kgu_babmat.service.ReviewService;
import kyonggi_girls.kgu_babmat.service.StoreService;
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
    private final StoreService storeService;


    public ReviewController(ReviewService reviewService, LoginService loginService, StoreService storeService) {
        this.reviewService = reviewService;
        this.loginService = loginService;
        this.storeService = storeService;
    }

    @GetMapping("review")
    public String myReview(@ModelAttribute StoreReview storeReview, Model model, HttpServletRequest request, @RequestParam String selectStoreName, @RequestParam String storeName) throws ExecutionException, InterruptedException {
        // session
        HttpSession session = request.getSession(false);
        if (session == null)
            return "redirect:/";

        User user = (User) session.getAttribute(SessionConst.sessionId);
        model.addAttribute("user", user);

        // store
        if ((selectStoreName == null) || (selectStoreName == "")) { // 일반 식당일 경우
            List<Store> store = storeService.getStore(storeName);
            model.addAttribute("store", store);
        } else { // 푸드코트 내의 식당일 경우
            List<Store> store = storeService.getInnerStore(selectStoreName, storeName);
            model.addAttribute("store", store);
        }
        model.addAttribute("storeName", storeName);
        model.addAttribute("selectStoreName", selectStoreName);

        // store review
        List<StoreReview> storeReviews = reviewService.showReview_all_store(storeName);
        for (StoreReview sr : storeReviews) {
            sr.setUsername(loginService.getUser(sr.getEmail()).getUsername());
        }
        model.addAttribute("storeReviews", storeReviews);
        return "reviews/review";
    }
}

