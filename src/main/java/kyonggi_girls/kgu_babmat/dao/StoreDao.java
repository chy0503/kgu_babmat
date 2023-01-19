package kyonggi_girls.kgu_babmat.dao;

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

    public static List<Store> getStores() throws ExecutionException, InterruptedException {
        List<Store> list = new ArrayList<>();
        ApiFuture<QuerySnapshot> future = db.collection(COLLECTION_NAME).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            list.add(document.toObject(Store.class));
        }
        return list;
    }

    public static List<Store> getStore(String storeName) throws ExecutionException, InterruptedException {
        List<Store> list = new ArrayList<>();
        DocumentReference documentReference = db.collection(COLLECTION_NAME).document(storeName);

        if (documentReference.get().get().get("selectStore").equals(true)) { // 푸드코트일 경우
            List<QueryDocumentSnapshot> documents = documentReference.collection(COLLECTION_NAME).get().get().getDocuments();
            for (QueryDocumentSnapshot doc : documents) {
                list.add(doc.toObject(Store.class));
            }
        } else { // 일반 식당일 경우
            DocumentSnapshot document = documentReference.get().get();
            list.add(document.toObject(Store.class));
        }

        return list;
    }

    public static List<Store> getInnerStore(String selectStoreName, String storeName) throws ExecutionException, InterruptedException {
        List<Store> list = new ArrayList<>();
        DocumentSnapshot document = db.collection(COLLECTION_NAME).document(selectStoreName).collection(COLLECTION_NAME).document(storeName).get().get();
        list.add(document.toObject(Store.class));
        return list;
    }

}