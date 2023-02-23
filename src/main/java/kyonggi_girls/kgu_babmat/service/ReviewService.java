package kyonggi_girls.kgu_babmat.service;


import kyonggi_girls.kgu_babmat.dto.StoreReview;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface ReviewService {

     List<StoreReview> showReview_all(String email) throws ExecutionException, InterruptedException;

     void createReview(String email, String storeName, String selectStore, String menu, int reviewScore, List<String> tags, String review, String writeTime ) throws ExecutionException, InterruptedException;
     List showReview_all_store(String storeName) throws ExecutionException, InterruptedException;
//     List Searching(String menu) throws ExecutionException, InterruptedException;
     List modifyReview(String email,String writeTime ) throws ExecutionException, InterruptedException;
     void update(String email, int reviewScore, List<String> tags, String review, String writeTime) throws ExecutionException, InterruptedException;

}

