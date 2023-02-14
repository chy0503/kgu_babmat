package kyonggi_girls.kgu_babmat.dao;

import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.firebase.cloud.FirestoreClient;
import kyonggi_girls.kgu_babmat.dto.StoreReview;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Repository
public class SearchDao {
    static Firestore db = FirestoreClient.getFirestore();



}