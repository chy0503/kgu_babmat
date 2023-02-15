package kyonggi_girls.kgu_babmat.controller;

import com.google.cloud.firestore.QueryDocumentSnapshot;
import kyonggi_girls.kgu_babmat.service.ReviewService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class SearchController {
    private final ReviewService reviewService;

    public SearchController(ReviewService reviewService) {

        this.reviewService = reviewService;
    }

}
