package kyonggi_girls.kgu_babmat.service;

import com.google.cloud.Timestamp;
import kyonggi_girls.kgu_babmat.dto.storeReview;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;

public interface ReviewService {

     List<storeReview> getReviews() throws Exception;

     String insertReview(String storeName,
                         int reviewScore, String review, String writeTime, String user_nickname ) throws Exception;
}

