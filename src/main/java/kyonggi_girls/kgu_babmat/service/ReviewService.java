package kyonggi_girls.kgu_babmat.service;


import kyonggi_girls.kgu_babmat.domain.storeReview;
import kyonggi_girls.kgu_babmat.dao.ReviewDao;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReviewService {

    private  final ReviewDao reviewDao;

    public ReviewService(ReviewDao reviewDao) {
        this.reviewDao = reviewDao;
    }

    public List<storeReview> getReviews() throws Exception {
        List<storeReview> reviewList = reviewDao.getReviews();
        return reviewDao.getReviews();
    }
}

