package kyonggi_girls.kgu_babmat.dao;


import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import kyonggi_girls.kgu_babmat.dto.Like;
import kyonggi_girls.kgu_babmat.dto.Store;
import kyonggi_girls.kgu_babmat.dto.StoreReview;
import kyonggi_girls.kgu_babmat.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;


@Repository
@Slf4j
public class ReviewDao {
    static Firestore db = FirestoreClient.getFirestore();

    /**
     * 작성된 리뷰 database 저장 및 수정
     */
    public static void createReview(String email, String storeName, String selectStore,
                                    String menu, int reviewScore, List<String> tags, String review,
                                    String writeTime) throws ExecutionException, InterruptedException {
        Map<String, Object> reviewmap = new HashMap<>();
        reviewmap.put("email", email);
        reviewmap.put("storeName", storeName);
        reviewmap.put("selectStore", selectStore);
        reviewmap.put("menu", menu);
        reviewmap.put("reviewScore", reviewScore);
        reviewmap.put("tags", tags);
        reviewmap.put("review", review);
        reviewmap.put("writeTime", writeTime);
        db.collection("reviews").document().set(reviewmap);
    }

    /**
     * 내가 쓴 리뷰 모아보기
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

    /**
     * 각 식당마다 리뷰 모아보기
     */
    public List showReview_all_store(String storeName) throws ExecutionException, InterruptedException {
        List<StoreReview> reviewList = new ArrayList<>();
        List<QueryDocumentSnapshot> documents = db.collection("reviews").whereEqualTo("storeName", storeName).get().get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            reviewList.add(document.toObject(StoreReview.class));
        }
        return reviewList;
    }

    /**
     * 리뷰 삭제 기능
     */
    public void modifyAndDeleteReview(String email, String writeTime) throws ExecutionException, InterruptedException {
        List<QueryDocumentSnapshot> documents = db.collection("reviews").whereEqualTo("email", email).whereEqualTo("writeTime", writeTime).get().get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            String reviewID = document.getId().toString();
            db.collection("reviews").document(reviewID).delete();
        }
    }

    /**
     * 수정할 리뷰 선택해서 불러오기
     */
    public StoreReview getReview(String email, String writeTime) throws ExecutionException, InterruptedException {
        List<QueryDocumentSnapshot> documents = db.collection("reviews").whereEqualTo("email", email).whereEqualTo("writeTime", writeTime).get().get().getDocuments();
        return documents.get(0).toObject(StoreReview.class);
    }

    /**
     * 리뷰 수정 기능
     */
    public void updates(String email, int reviewScore, List<String> tags, String review, String writeTime) throws ExecutionException, InterruptedException {
        List<QueryDocumentSnapshot> documents = db.collection("reviews").whereEqualTo("email", email).whereEqualTo("writeTime", writeTime).get().get().getDocuments();
        Map<String, Object> updates = new HashMap<>();
        updates.put("reviewScore", reviewScore);
        updates.put("tags", tags);
        updates.put("review", review);
        updates.put("writeTime", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        // asynchronously update doc
        db.collection("reviews").document(documents.get(0).getId()).update(updates);
    }

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












