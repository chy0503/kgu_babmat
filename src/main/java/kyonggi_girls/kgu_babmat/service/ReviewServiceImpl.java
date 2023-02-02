package kyonggi_girls.kgu_babmat.service;

import com.google.cloud.Timestamp;
import kyonggi_girls.kgu_babmat.dao.ReviewDao;
import kyonggi_girls.kgu_babmat.dto.storeReview;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
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
                               int reviewScore, String review, String writeTime, String user_nickname) throws Exception {
        return reviewDao.setRequiresId(storeName, reviewScore, review, writeTime, user_nickname);
    }
}
