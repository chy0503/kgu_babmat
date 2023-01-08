package kyonggi_girls.kgu_babmat.controller.reviews;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReviewController {
    @GetMapping("review")
    public String review() {
        return "reviews/review";
    }
}
