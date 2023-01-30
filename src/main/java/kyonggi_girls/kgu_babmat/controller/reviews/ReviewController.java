package kyonggi_girls.kgu_babmat.controller.reviews;

import kyonggi_girls.kgu_babmat.dto.storeReview;
import kyonggi_girls.kgu_babmat.service.ReviewService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews/review")
    public String reviewPage(Model model) throws Exception {
        List<storeReview> reviewList = reviewService.getReviews();
        model.addAttribute("reviewList", reviewList);
        return "/reviews/review";
    }


}

