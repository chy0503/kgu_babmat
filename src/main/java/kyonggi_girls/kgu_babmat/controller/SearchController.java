package kyonggi_girls.kgu_babmat.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kyonggi_girls.kgu_babmat.dto.StoreReview;
import kyonggi_girls.kgu_babmat.dto.User;
import kyonggi_girls.kgu_babmat.service.LoginService;
import kyonggi_girls.kgu_babmat.service.ReviewService;
import kyonggi_girls.kgu_babmat.session.SessionConst;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Controller
public class SearchController {
    private final ReviewService reviewService;
    private final LoginService loginService;

    public SearchController(ReviewService reviewService, LoginService loginService) {
        this.reviewService = reviewService;
        this.loginService = loginService;
    }

    @GetMapping("search")
    public String search(@RequestParam("search") String search, Model model, HttpServletRequest request) throws ExecutionException, InterruptedException {
        // session
        HttpSession session = request.getSession(false);
        if (session == null)
            return "redirect:/";

        User user = (User) session.getAttribute(SessionConst.sessionId);
        model.addAttribute("user", user);

        List<StoreReview> storeReviews = reviewService.Searching(search);
        for (StoreReview sr : storeReviews) {
            sr.setUsername(loginService.getUser(sr.getEmail()).getUsername());
        }
        model.addAttribute("storeReviews", storeReviews);
        model.addAttribute("search", search);
        return "search";
    }
}
