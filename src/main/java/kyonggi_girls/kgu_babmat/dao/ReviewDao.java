package kyonggi_girls.kgu_babmat.dao;



import kyonggi_girls.kgu_babmat.dto.storeReview;



import java.util.List;
import java.util.concurrent.ExecutionException;


public interface ReviewDao {

    List<storeReview> getReviews() throws ExecutionException, InterruptedException;


    String setRequiresId(String storeName, int reviewScore, String review,
                         String writeTime, String email, String menu) throws Exception;
    List<storeReview> getUserPosts(String user_nickname);

//    void deleteCollection(CollectionReference collection, int batchSize);
}

