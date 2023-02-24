package kyonggi_girls.kgu_babmat.dao;

import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.firebase.cloud.FirestoreClient;
import kyonggi_girls.kgu_babmat.dto.Menu;
import kyonggi_girls.kgu_babmat.dto.StoreReview;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;

@Repository
@Slf4j
public class MenuLankingDao {

    static Firestore db = FirestoreClient.getFirestore();

//    public static List<StoreReview> showAllUsersReviews() throws ExecutionException, InterruptedException {
//        UserDao userDao = new UserDao();
//        List<User> users = userDao.getUsers();
//        List<StoreReview> list = new ArrayList<>();
//        for (User user : users) {
//            List<QueryDocumentSnapshot> documents = db.collection("users").document(user.getEmail()).collection("reviews").get().get().getDocuments();
//            for (QueryDocumentSnapshot document : documents) {
//                list.add(document.toObject(StoreReview.class));
//            }
//        }
//        return list;
//    }

    public static List<Menu> showMenuLanking() throws ExecutionException, InterruptedException {
        // 리뷰 저장
        List<StoreReview> reviews = new ArrayList<>();
        List<QueryDocumentSnapshot> documents = db.collection("reviews").get().get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            reviews.add(document.toObject(StoreReview.class));
        }

        // 메뉴 이름만 추출
        List<String> menus = new ArrayList<>();
        for (StoreReview review : reviews) {
            menus.add(review.getMenu());
        }

        // 메뉴 이름 중복 제거
        Set<String> set = new HashSet<String>(menus);
        menus = new ArrayList<String>(set);

        // 메뉴별 리뷰점수 총합, 개수 저장
        float[] score = new float[menus.size()];
        int[] cnt = new int[menus.size()];
        for (StoreReview review : reviews) {
            score[menus.indexOf(review.getMenu())] += review.getReviewScore();
            cnt[menus.indexOf(review.getMenu())] += 1;
        }
        // 메뉴별 리뷰평균 구하고 내림차순으로 정렬해서 반환
        List<Menu> list = new ArrayList<>();
        for (int i = 0; i < score.length; i++) {
            score[i] /= cnt[i];
            Menu menu = new Menu();
            menu.setName(menus.get(i));
            menu.setReviewScore(score[i]);
            menu.setReviewNum(cnt[i]);

            for (StoreReview review : reviews) {
                if (review.getMenu() == menu.getName()) {
                    if (review.getSelectStore().length() > 0) {
                        menu.setSelectStore(review.getSelectStore());
                    }
                    menu.setStoreName(review.getStoreName());
                    break;
                }
            }

            if (list != null && list.size()>0) { // 리스트에 값이 존재하고 0보다 클 때
                if (list.get(list.size()-1).getReviewScore() < menu.getReviewScore()) { // 리스트에 담긴 평균이 나보다 작다면
                    int j = list.size()-1;
                    while ((list.get(j).getReviewScore() < menu.getReviewScore()) && (j>0)) { // 리스트에 나보다 큰 평균이 나올 까지 찾음
                        j--;
                    }
                    list.add(j, menu);
                } else
                    list.add(menu);
            } else
                list.add(menu);
        }
        return list;
    }
}


