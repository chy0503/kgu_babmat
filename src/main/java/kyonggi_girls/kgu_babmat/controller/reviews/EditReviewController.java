package kyonggi_girls.kgu_babmat.controller.reviews;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EditReviewController {
    @GetMapping("editReview")
    public String editReview() {
        return "reviews/editReview";
    }
}
