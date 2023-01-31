package kyonggi_girls.kgu_babmat.dao;

import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.firebase.cloud.FirestoreClient;
import kyonggi_girls.kgu_babmat.dto.Like;
import kyonggi_girls.kgu_babmat.dto.Menu;
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
    public static void updateLike(String email, String menu, String selectStoreName, String storeName) throws ExecutionException, InterruptedException {
        Like like = new Like();
        like.setMenu(menu);
        like.setSelectStore(selectStoreName);
        like.setStore(storeName);

        Like likeMenu = db.collection("users").document(email).collection("likes").document(menu).get().get().toObject(Like.class);
        System.out.println("likeMenu = " + likeMenu);
        if (likeMenu == null) {
            db.collection("users").document(email).collection("likes").document(menu).set(like);
            System.out.println("생성");
        } else {
            db.collection("users").document(email).collection("likes").document(menu).delete();
            System.out.println("삭제");
        }
    }

    /**
     * 좋아요 눌린 메뉴 불러오기 - 메뉴 좋아요 체크 용도
     */
    public static List<Menu> showLike_menu(String email, String menu) throws ExecutionException, InterruptedException {
        List<Menu> menuList = new ArrayList<>();
        List<Like> likeList = new ArrayList<>();
        List<QueryDocumentSnapshot> documents = db.collection("users").document(email).collection("likes").get().get().getDocuments();
        for(QueryDocumentSnapshot document :documents) {
            likeList.add(document.toObject(Like.class));
        }



        return menuList;
    }

    /**
     * 좋아요 눌린 메뉴 불러오기 - 좋아요 모아보기 용도
     */
}
