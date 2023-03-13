package kyonggi_girls.kgu_babmat.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kyonggi_girls.kgu_babmat.dto.StoreReview;
import kyonggi_girls.kgu_babmat.service.ReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Controller
public class SearchController {
    private final ReviewService reviewService;

    public SearchController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("search")
    public String search(@RequestParam("search") String search, Model model, HttpServletRequest request) throws ExecutionException, InterruptedException {
        HttpSession session = request.getSession();
        if (session == null)
            return "redirect:/";

        List<StoreReview> storeReviews = reviewService.Searching(search);
        model.addAttribute("storeReviews", storeReviews);
        model.addAttribute("search", search);
        return "search";
    }
}
