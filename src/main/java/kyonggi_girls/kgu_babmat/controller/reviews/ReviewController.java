package kyonggi_girls.kgu_babmat.controller.reviews;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kyonggi_girls.kgu_babmat.dto.StoreReview;
import kyonggi_girls.kgu_babmat.dto.User;
import kyonggi_girls.kgu_babmat.service.ReviewService;
import kyonggi_girls.kgu_babmat.session.SessionConst;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.List;
import java.util.concurrent.ExecutionException;


@Controller
public class ReviewController {

    private final ReviewService reviewService;


    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;

    }


    @GetMapping("review")
    public String ReviewCollect( Model model, HttpServletRequest request) throws ExecutionException, InterruptedException {
        // session
        HttpSession session = request.getSession(false);
        if (session == null)
            return "redirect:/";
        User user = (User) session.getAttribute(SessionConst.sessionId);
        model.addAttribute("user", user);

        List<StoreReview> reviewList = reviewService.showReview_all(user.getEmail());
        model.addAttribute("reviewList", reviewList);

        return "reviews/review";
    }

    @PostMapping("review")
    public String reviewCollectd(@ModelAttribute StoreReview storeReview, HttpServletRequest request) throws Exception {
        // session
        HttpSession session = request.getSession(false);
        if (session == null)
            return "redirect:/";
        User user = (User) session.getAttribute(SessionConst.sessionId);
        reviewService.updateReview(user.getEmail(), storeReview.getStoreName(), storeReview.getSelectStore(), storeReview.getMenu(),
                storeReview.getReviewScore(), storeReview.getReview());
        System.out.println("리뷰 모아보기 : " + reviewService.showReview_all(user.getEmail()));
        return "redirect:/reviews/review";
    }



}

