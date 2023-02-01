package kyonggi_girls.kgu_babmat.service;

import kyonggi_girls.kgu_babmat.dto.storeReview;
import org.springframework.http.ResponseEntity;


import java.util.List;

public interface ReviewService {

     List<storeReview> getReviews() throws Exception;

     String insertReview(String storeName,
                         int reviewScore, String review, String writeTime, String email, String menu) throws Exception;

     ResponseEntity<List<storeReview>> getUserPosts(String user_nickname);
}

