package kyonggi_girls.kgu_babmat.controller.reviews;


import kyonggi_girls.kgu_babmat.dto.storeReview;
import kyonggi_girls.kgu_babmat.service.ReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WriteReviewController {
    private final ReviewService reviewService;

    public WriteReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }
    @GetMapping("reviews/writeReview")
    public String reviewPage() {
        return "reviews/writeReview";
    }
    @PostMapping("/reviewCreate")
    public String login(@ModelAttribute storeReview storeReview) throws Exception {
        String writeReview = reviewService.insertReview(storeReview.getReviewId(), storeReview.getStoreName(),
        storeReview.getReviewScore(), storeReview.getReview(), storeReview.getWriteTime(), storeReview.getUser_nickname());

        if (writeReview == null) {
            return "redirect:/";
        }else{
            //writeReview
            return "redirect:reviews/review";
        }

    }

}
