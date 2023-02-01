package kyonggi_girls.kgu_babmat.service;


import kyonggi_girls.kgu_babmat.dao.ReviewDao;
import kyonggi_girls.kgu_babmat.dto.storeReview;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewDao reviewDao;

    public ReviewServiceImpl(ReviewDao reviewDao) {

        this.reviewDao = reviewDao;
    }

    @Override
    public List<storeReview> getReviews() throws Exception {

        return reviewDao.getReviews();
    }

    @Override
    public String insertReview(String storeName,
                               int reviewScore, String review, String writeTime, String email, String menu) throws Exception {
        return reviewDao.setRequiresId(storeName, reviewScore, review, writeTime, email, menu);
    }
    @Override
    public ResponseEntity<List<storeReview>> getUserPosts(String user_nickname) {
        return new ResponseEntity<>(reviewDao.getUserPosts(user_nickname), HttpStatus.OK);
    }


}
