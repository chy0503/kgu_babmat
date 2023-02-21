package kyonggi_girls.kgu_babmat.dao;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import kyonggi_girls.kgu_babmat.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Repository
@Slf4j
public class UserDao {

    public static final String COLLECTION_NAME = "users";
    static Firestore db = FirestoreClient.getFirestore();

    public static int insertUser(String email, String username) throws Exception {
        DocumentReference docRef = db.collection("users").document(email);
        Map<String, Object> data = new HashMap<>();
        data.put("username", username);
        data.put("email", email);
        ApiFuture<WriteResult> result = docRef.set(data);
        System.out.println("Update time : " + result.get().getUpdateTime());
        //db.collection("users").add(data);
        return 0;
    }

    public static int nickname(String username) throws Exception {
        DocumentReference docRef = db.collection("users").document();
        Map<String, Object> data = new HashMap<>();
        data.put("username", username);
       // data.put("email", email);
        ApiFuture<WriteResult> result = docRef.set(data);
       // System.out.println("Update time : " + result.get().getUpdateTime());
        //db.collection("users").add(data);
        return 0;
    }

    public List<User> getUsers() throws ExecutionException, InterruptedException {
        List<User> list = new ArrayList<>();
        List<QueryDocumentSnapshot> documents = db.collection(COLLECTION_NAME).get().get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            list.add(document.toObject(User.class));
        }
        return list;
    }

    public boolean isUser(String email) throws ExecutionException, InterruptedException {
        List<User> userList = getUsers();
        for (User user : userList) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public User getUser(String email) throws ExecutionException, InterruptedException {
        return db.collection(COLLECTION_NAME).document(email).get().get().toObject(User.class);
    }
}