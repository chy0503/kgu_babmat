package kyonggi_girls.kgu_babmat.dao;

import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.firebase.cloud.FirestoreClient;
import kyonggi_girls.kgu_babmat.dto.StoreReview;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Repository
public class SearchDao {
    static Firestore db = FirestoreClient.getFirestore();

    public List SearchReview(String search) throws ExecutionException, InterruptedException {
        List<StoreReview> List = new ArrayList<>();
            List<QueryDocumentSnapshot> documents = db.collection("reviews").get().get().getDocuments();
            for(QueryDocumentSnapshot document :documents) {
                StoreReview review = document.toObject(StoreReview.class);
                if (review.getMenu().contains(search)) {
                    List.add(review);
                }
            }
        return List;
    }
}