package kyonggi_girls.kgu_babmat.dao;

import com.google.cloud.Timestamp;
import kyonggi_girls.kgu_babmat.dto.storeReview;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutionException;


public interface ReviewDao {

    List<storeReview> getReviews() throws ExecutionException, InterruptedException;


    String setRequiresId(String storeName, int reviewScore, String review,
                         String writeTime, String user_nickname) throws Exception;
}

