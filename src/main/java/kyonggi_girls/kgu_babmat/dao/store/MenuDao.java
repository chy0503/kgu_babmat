package kyonggi_girls.kgu_babmat.dao.store;

import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import kyonggi_girls.kgu_babmat.dto.Menu;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Repository
@Slf4j
public class MenuDao {

    public static final String COLLECTION_NAME = "stores";
    static Firestore db = FirestoreClient.getFirestore();

    /**
     * 식당 메뉴 불러오기
     */
    public static List<Menu> getMenu(String selectStoreName, String storeName) throws ExecutionException, InterruptedException {
        List<Menu> list = new ArrayList<>();
        if (selectStoreName == null) {
            List<QueryDocumentSnapshot> menus = db.collection(COLLECTION_NAME).document(storeName).collection("menus").orderBy("price", Query.Direction.DESCENDING).get().get().getDocuments();
            for (QueryDocumentSnapshot menu : menus) {
                list.add(menu.toObject(Menu.class));
            }
        } else {
            List<QueryDocumentSnapshot> menus = db.collection(COLLECTION_NAME).document(selectStoreName).collection(COLLECTION_NAME).document(storeName).collection("menus").orderBy("price", Query.Direction.DESCENDING).get().get().getDocuments();
            for (QueryDocumentSnapshot menu : menus) {
                list.add(menu.toObject(Menu.class));
            }
        }
        return list;
    }
}