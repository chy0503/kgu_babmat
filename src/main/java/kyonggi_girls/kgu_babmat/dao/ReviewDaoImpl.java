package kyonggi_girls.kgu_babmat.dao;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import kyonggi_girls.kgu_babmat.dto.storeReview;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;


@Repository
@Slf4j
public class ReviewDaoImpl implements ReviewDao {

    public static final String COLLECTION_NAME = "reviews";
    Firestore db = FirestoreClient.getFirestore();

    @Override
    public List<storeReview> getReviews() throws ExecutionException, InterruptedException {
        List<storeReview> list = new ArrayList<>();
        ApiFuture<QuerySnapshot> future = db.collection(COLLECTION_NAME).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            list.add(document.toObject(storeReview.class));
        }
        return list;
    }
    @Override
    public String setRequiresId(String reviewId, String storeName, String reviewScore, String review,
                                String writeTime, String user_nickname) throws Exception {
        Map<String,Object> tmp = new HashMap<>();
        tmp.put("reviewId", reviewId);
        tmp.put("storeName",storeName);
        tmp.put("reviewScore", reviewScore);
        tmp.put("review",review);
        tmp.put("writeTime", writeTime);
        tmp.put("user_nickname", user_nickname);
       ApiFuture<DocumentReference> LoadReview = db.collection("reviews").add(tmp);
       System.out.println("Add with Id : " + LoadReview.get().getId());
       LoadReview.get().getId();
        return reviewId;
    }

}




