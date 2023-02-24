package kyonggi_girls.kgu_babmat.dao;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;

@Repository
public class SearchDao {
    static Firestore db = FirestoreClient.getFirestore();

}
