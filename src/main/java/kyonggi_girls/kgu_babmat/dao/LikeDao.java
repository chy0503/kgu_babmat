package kyonggi_girls.kgu_babmat.dao;

import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.firebase.cloud.FirestoreClient;
import kyonggi_girls.kgu_babmat.dto.Like;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Repository
@Slf4j
public class LikeDao {
    static Firestore db = FirestoreClient.getFirestore();

    /**
     * 좋아요 반영(저장 및 삭제)
     */
    public static void updateLike(String email, String menu, long price, String selectStoreName, String storeName) throws ExecutionException, InterruptedException {
        Like like = new Like();
        like.setMenu(menu);
        like.setPrice(price);
        like.setSelectStore(selectStoreName);
        like.setStore(storeName);

        Like likeMenu = db.collection("users").document(email).collection("likes").document(menu).get().get().toObject(Like.class);
        if (likeMenu == null)
            db.collection("users").document(email).collection("likes").document(menu).set(like);
        else
            db.collection("users").document(email).collection("likes").document(menu).delete();
    }

    /**
     * 좋아요 눌린 메뉴 불러오기 - 좋아요 모아보기 용도
     */
    public static List<Like> showLike_all(String email) throws ExecutionException, InterruptedException {
        List<Like> likeList = new ArrayList<>();
        List<QueryDocumentSnapshot> documents = db.collection("users").document(email).collection("likes").get().get().getDocuments();
        for(QueryDocumentSnapshot document :documents) {
            likeList.add(document.toObject(Like.class));
        }
        return likeList;
    }

    /**
     * 좋아요 눌린 메뉴 불러오기 - 메뉴 좋아요 체크 용도
     */
    public static List showLike_menu(String store, String email) throws ExecutionException, InterruptedException {
        List likeList = new ArrayList<>();

        List<QueryDocumentSnapshot> documents = db.collection("users").document(email).collection("likes").whereEqualTo("store", store).get().get().getDocuments();
        for(QueryDocumentSnapshot document :documents) {
            likeList.add(document.toObject(Like.class).getMenu());
        }
        return likeList;
    }
}
