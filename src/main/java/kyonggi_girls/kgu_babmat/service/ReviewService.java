package kyonggi_girls.kgu_babmat.service;


import kyonggi_girls.kgu_babmat.dto.StoreReview;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface ReviewService {

     List<StoreReview> showReview_all(String email) throws ExecutionException, InterruptedException;

     void updateReview(String email, String storeName, String selectStore, String menu, int reviewScore, String review ) throws ExecutionException, InterruptedException;

//     Long storeReview(StoreReview storeReview);
//     void deleteReview(String id);

}

