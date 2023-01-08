package kyonggi_girls.kgu_babmat.controller.reviews;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WriteReviewController {
    @GetMapping("writeReview")
    public String writeReview() {
        return "reviews/writeReview";
    }
}
