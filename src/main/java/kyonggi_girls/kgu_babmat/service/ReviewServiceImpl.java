package kyonggi_girls.kgu_babmat.service;


import kyonggi_girls.kgu_babmat.dao.ReviewDao;
import kyonggi_girls.kgu_babmat.dao.SearchDao;
import kyonggi_girls.kgu_babmat.dto.StoreReview;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewDao reviewDao;
    private final SearchDao searchDao;


    @Override
    public void createReview(String email, String storeName, String selectStore, String menu, int reviewScore,
                             List<String> tags, String review, String writeTime) throws ExecutionException, InterruptedException {

        reviewDao.createReview(email, storeName, selectStore, menu, reviewScore, tags, review, writeTime);
    }

    @Override
    public List<StoreReview> showReview_all(String email) throws ExecutionException, InterruptedException {
        return reviewDao.showReview_all(email);
    }

    @Override
    public List showReview_all_store(String storeName) throws ExecutionException, InterruptedException {
        return reviewDao.showReview_all_store(storeName);
    }

//   @Override
//   public List Searching(String menu) throws ExecutionException, InterruptedException{
//        return searchDao.Searching(menu);
//   }

   @Override
   public List modifyReview(String email, String writeTime) throws ExecutionException, InterruptedException {
        return reviewDao.modifyAndDeleteReview(email, writeTime);
   }
   @Override
   public void update(String email, int reviewScore, List<String> tags, String review, String writeTime) throws ExecutionException, InterruptedException {
        reviewDao.updates(email, reviewScore, tags, review, writeTime);
   }


}
