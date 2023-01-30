package kyonggi_girls.kgu_babmat.service;

import kyonggi_girls.kgu_babmat.dto.storeReview;

import java.util.List;

public interface ReviewService {

     List<storeReview> getReviews() throws Exception;

     String insertReview(String reviewId, String storeName,
                       String reviewScore, String review, String writeTime, String user_nickname ) throws Exception;
}

