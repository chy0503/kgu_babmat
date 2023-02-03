package kyonggi_girls.kgu_babmat.controller.reviews;


import kyonggi_girls.kgu_babmat.service.ReviewService;


import org.springframework.stereotype.Controller;


@Controller
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }




}

