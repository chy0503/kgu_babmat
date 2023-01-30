package kyonggi_girls.kgu_babmat.service;

import kyonggi_girls.kgu_babmat.dao.ReviewDao;
import kyonggi_girls.kgu_babmat.dto.storeReview;
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
    public String insertReview(String reviewId, String storeName,
                             String reviewScore, String review, String writeTime, String user_nickname) throws Exception{
     return reviewDao.setRequiresId(reviewId, storeName,reviewScore, review, writeTime, user_nickname);
    }
}
