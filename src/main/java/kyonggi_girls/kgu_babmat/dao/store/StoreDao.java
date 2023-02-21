package kyonggi_girls.kgu_babmat.dao.store;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import kyonggi_girls.kgu_babmat.dto.Store;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Repository
@Slf4j
public class StoreDao {

    public static final String COLLECTION_NAME = "stores";
    static Firestore db = FirestoreClient.getFirestore();

    /**
     * 식당 리스트 모아보기(관리자용)
     */
    public static List<Store> getStores() throws ExecutionException, InterruptedException {
        List<Store> list = new ArrayList<>();
        ApiFuture<QuerySnapshot> future = db.collection(COLLECTION_NAME).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            list.add(document.toObject(Store.class));
        }
        return list;
    }

    /**
     * 식당 정보 불러오기: 푸드코트, 일반 식당
     */
    public static List<Store> getStore(String storeName) throws ExecutionException, InterruptedException {
        List<Store> list = new ArrayList<>();
        DocumentReference documentReference = db.collection(COLLECTION_NAME).document(storeName);

        if (documentReference.get().get().get("selectStore").equals(true)) {
            List<QueryDocumentSnapshot> documents = documentReference.collection(COLLECTION_NAME).get().get().getDocuments();
            for (QueryDocumentSnapshot doc : documents) {
                list.add(doc.toObject(Store.class));
            }
        } else {
            DocumentSnapshot document = documentReference.get().get();
            list.add(document.toObject(Store.class));
        }
        return list;
    }

    /**
     * 식당 정보 불러오기: 푸드코트 내의 식당
     */
    public static List<Store> getInnerStore(String selectStoreName, String storeName) throws ExecutionException, InterruptedException {
        List<Store> list = new ArrayList<>();
        DocumentSnapshot document = db.collection(COLLECTION_NAME).document(selectStoreName).collection(COLLECTION_NAME).document(storeName).get().get();
        list.add(document.toObject(Store.class));
        return list;
    }
}