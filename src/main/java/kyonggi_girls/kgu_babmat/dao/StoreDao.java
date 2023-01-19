package kyonggi_girls.kgu_babmat.dao;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
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

    public static List<Store> getStores() throws ExecutionException, InterruptedException {
        List<Store> list = new ArrayList<>();
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = db.collection(COLLECTION_NAME).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            list.add(document.toObject(Store.class));
        }
        return list;
    }

    public static List<Store> getStore(String storeName) throws ExecutionException, InterruptedException {
        List<Store> list = new ArrayList<>();
        Firestore db = FirestoreClient.getFirestore();
        DocumentSnapshot document = db.collection(COLLECTION_NAME).document(storeName).get().get();

        if (document.get("selectStore").equals(true)) {
            List<QueryDocumentSnapshot> documents = db.collection(COLLECTION_NAME).document(storeName).collection(COLLECTION_NAME).get().get().getDocuments();
            for (QueryDocumentSnapshot doc : documents) {
                list.add(doc.toObject(Store.class));
            }
        } else {
            list.add(document.toObject(Store.class));
        }

        return list;
    }

    public static List<Store> getStore2(String storeName, String selectStore) throws ExecutionException, InterruptedException {
        List<Store> list = new ArrayList<>();
        Firestore db = FirestoreClient.getFirestore();
        DocumentSnapshot document = db.collection(COLLECTION_NAME).document(selectStore).collection(COLLECTION_NAME).document(storeName).get().get();
        list.add(document.toObject(Store.class));

        return list;
    }

}