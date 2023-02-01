package kyonggi_girls.kgu_babmat.dao;

import com.google.api.core.ApiFuture;

import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import kyonggi_girls.kgu_babmat.dto.storeReview;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;
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
    public String setRequiresId(String storeName, int reviewScore, String review,
                                String writeTime, String email, String menu) throws Exception {
        Map<String, Object> tmp = new HashMap<>();
        tmp.put("storeName", storeName);
        tmp.put("reviewScore", reviewScore);
        tmp.put("review", review);
        tmp.put("writeTime", writeTime);
        tmp.put("user_email", email);
        tmp.put("menu", menu);
        ApiFuture<DocumentReference> LoadReview = db.collection("reviews").add(tmp);
        System.out.println("Add with Id : " + LoadReview.get().getId());
        return LoadReview.get().getId();
    }

    public List<storeReview> getUserPosts(String user_nickname) {
        List<storeReview> reviewList = new ArrayList<>();
        CollectionReference ref = db.collection("reviews");
        ApiFuture<QuerySnapshot> future = ref.whereEqualTo("user_nickname", user_nickname).get();

        try {
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();
            for (QueryDocumentSnapshot document : documents) {
                storeReview reviews = document.toObject(storeReview.class);
                reviewList.add(reviews);
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return reviewList;
    }

}




