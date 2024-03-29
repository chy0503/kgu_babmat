package kyonggi_girls.kgu_babmat.dao;

import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.firebase.cloud.FirestoreClient;
import kyonggi_girls.kgu_babmat.dto.OAuthAttributes;
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

    public List<User> getUsers() throws ExecutionException, InterruptedException {
        List<User> list = new ArrayList<>();
        List<QueryDocumentSnapshot> documents = db.collection(COLLECTION_NAME).get().get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            list.add(document.toObject(User.class));
        }
        return list;
    }

    public boolean isUserExit(String email) throws ExecutionException, InterruptedException {
        List<User> userList = getUsers();
        for (User user : userList) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public User getUser(String email) throws ExecutionException, InterruptedException {
        return db.collection(COLLECTION_NAME).document(email)
                .get().get().toObject(User.class);
    }

    public User getUserInfo(String email) throws ExecutionException, InterruptedException {
        List<QueryDocumentSnapshot> documents = db.collection("users").whereEqualTo("email", email).get().get().getDocuments();
        return documents.get(0).toObject(User.class);
    }

    public void updateUser(String email, String username) throws ExecutionException, InterruptedException {
        Map<String, Object> updates = new HashMap<>();
        updates.put("username", username);
        // asynchronously update doc
        db.collection("users").document(email).update(updates);
    }

    public void deleteUser(String email) throws ExecutionException, InterruptedException {
        db.collection("users").document(email).delete();
        List<QueryDocumentSnapshot> documents = db.collection("reviews").whereEqualTo("email", email).get().get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            String reviewID = document.getId().toString();
            db.collection("reviews").document(reviewID).delete();
        }
    }

    public User createUser(String email, String username) throws ExecutionException, InterruptedException {
        User user = new User();
        user.setEmail(email);
        user.setUsername(username);
        db.collection("users").document(email).set(user);

        return user;
    }
}