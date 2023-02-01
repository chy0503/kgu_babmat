package kyonggi_girls.kgu_babmat.controller.reviews;

import jakarta.servlet.http.HttpSession;
import kyonggi_girls.kgu_babmat.dto.storeReview;
import kyonggi_girls.kgu_babmat.service.ReviewService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.List;


@Controller
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews/review")
    public String reviewPage(Model model
    ) throws Exception {
        List<storeReview> reviewList = reviewService.getReviews();
        model.addAttribute("reviewList", reviewList);
        return "/reviews/review";
    }


    @GetMapping("/posts/{user_nickname}")
    public ResponseEntity<List<storeReview>> getUserPosts(@PathVariable String user_nickname, HttpSession session) {
        String sessionNickname = (String) session.getAttribute("user_nickname");
        if (!user_nickname.equals(sessionNickname)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        return reviewService.getUserPosts(user_nickname);
    }

}

