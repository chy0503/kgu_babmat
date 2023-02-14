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
    public static final String COLLECTION_NAME = "reviews";
    static Firestore db = FirestoreClient.getFirestore();

    /**
     * 작성된 리뷰 database 저장
     */

    public static void updateReview(String email, String storeName, String selectStore, String menu, int reviewScore, String review) throws ExecutionException, InterruptedException {
        Map<String, Object> reviewmap = new HashMap<>();
        reviewmap.put("email", email);
        reviewmap.put("storeName", storeName);
        reviewmap.put("selectStore", selectStore);
        reviewmap.put("menu", menu);
        reviewmap.put("reviewScore", reviewScore);
        reviewmap.put("review", review);
        reviewmap.put("writeTime", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));
        db.collection("reviews").add(reviewmap);

    }
    /**
     * 작성된 리뷰 database에서 불러오기
     */

    public static List<StoreReview> showReview_all(String email) throws ExecutionException, InterruptedException {
        List<StoreReview> List = new ArrayList<>();
        List<QueryDocumentSnapshot> documents = db.collection("reviews").whereEqualTo("email", email)
                .get().get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            List.add(document.toObject(StoreReview.class));
        }
        return List;
    }





//    public static void delete(String id){
//        db.collection("users").document(id).delete();
//    }
//



}



