package kyonggi_girls.kgu_babmat.dao;

import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import kyonggi_girls.kgu_babmat.dto.StoreReview;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ExecutionException;


@Repository
@Slf4j
public class ReviewDao {

    static Firestore db = FirestoreClient.getFirestore();

    public static void updateReview(String email, String menu,  String review, int reviewScore) throws ExecutionException, InterruptedException {
        StoreReview storeReview = new StoreReview();
        storeReview.setMenu(menu);
        storeReview.setReviewScore(reviewScore);
        storeReview.setReview(review);
        storeReview.setWriteTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        StoreReview collectReview = db.collection("user").document(email).collection("reviews").document().get().get().toObject(StoreReview.class);
        if (collectReview == null)
            db.collection("users").document(email).collection("reviews").document().set(storeReview);
        else
            db.collection("users").document(email).collection("reviews").document().delete();

    }

    public static List<StoreReview> showReview_all(String email) throws ExecutionException, InterruptedException {
        List<StoreReview> List = new ArrayList<>();
        List<QueryDocumentSnapshot> documents = db.collection("users").document(email)
                .collection("reviews")
                .orderBy("writeTime", Query.Direction.DESCENDING)
                .get().get().getDocuments();
        for(QueryDocumentSnapshot document :documents) {
            List.add(document.toObject(StoreReview.class));
        }
        return List;
    }



}




