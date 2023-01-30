package kyonggi_girls.kgu_babmat.dao;

import kyonggi_girls.kgu_babmat.dto.storeReview;

import java.util.List;
import java.util.concurrent.ExecutionException;


public interface ReviewDao {

    List<storeReview> getReviews() throws ExecutionException, InterruptedException;


    String setRequiresId(String reviewId, String storeName, String reviewScore, String review,
                         String writeTime, String user_nickname) throws Exception;
}

