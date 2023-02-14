package kyonggi_girls.kgu_babmat.service;



import kyonggi_girls.kgu_babmat.dao.ReviewDao;
import kyonggi_girls.kgu_babmat.dao.SearchDao;
import kyonggi_girls.kgu_babmat.dto.StoreReview;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewDao reviewDao;
    private final SearchDao searchDao;


    public ReviewServiceImpl(ReviewDao reviewDao, SearchDao searchDao) {

        this.reviewDao = reviewDao;
        this.searchDao = searchDao;

    }

    @Override
    public void updateReview(String email, String storeName, String selectStore, String menu, int reviewScore, String review ) throws ExecutionException, InterruptedException {

        reviewDao.updateReview(email, storeName, selectStore, menu, reviewScore, review);
    }

    @Override
    public List<StoreReview> showReview_all(String email) throws ExecutionException, InterruptedException {
        return reviewDao.showReview_all(email);
    }

//    @Override
//    public void deleteReview(String id){
//        reviewDao.delete(id);
//    }

}
