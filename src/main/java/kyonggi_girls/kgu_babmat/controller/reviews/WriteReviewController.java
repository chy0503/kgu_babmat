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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class WriteReviewController {
    private final ReviewService reviewService;

    public WriteReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("writeReview")
    public String reviewPage(Model model, HttpServletRequest request) throws Exception {
        // session
        HttpSession session = request.getSession(false);
        if (session == null)
            return "redirect:/";
        User user = (User) session.getAttribute(SessionConst.sessionId);
        model.addAttribute("user", user);
        List storeReview = reviewService.showReview_all(user.getEmail());
        model.addAttribute("storeReview", storeReview);
        return "reviews/writeReview";
    }

    @PostMapping("/reviewCreate")
    public String writeReview(@ModelAttribute StoreReview storeReview, RedirectAttributes redirect, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession(false);
        if (session == null)
            return "redirect:/";
        User user = (User) session.getAttribute(SessionConst.sessionId);
        reviewService.updateReview(user.getEmail(), storeReview.getMenu(), storeReview.getReview(), storeReview.getReviewScore());
//        redirect.addAttribute("storeName", storeReview.getStoreName());
        return "redirect:/main";
    }

}
