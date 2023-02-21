package kyonggi_girls.kgu_babmat.service;


import kyonggi_girls.kgu_babmat.dao.ReviewDao;
import kyonggi_girls.kgu_babmat.dto.StoreReview;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewDao reviewDao;

    public ReviewServiceImpl(ReviewDao reviewDao) {

        this.reviewDao = reviewDao;
    }

    @Override
    public void updateReview(String email, String menu,  String review, int reviewScore) throws ExecutionException, InterruptedException {

        reviewDao.updateReview(email, menu,  review, reviewScore);
    }

    @Override
    public List<StoreReview> showReview_all(String email) throws ExecutionException, InterruptedException {
        return reviewDao.showReview_all(email);
    }



}
