package kyonggi_girls.kgu_babmat.dto;



import lombok.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;


@Data
@NoArgsConstructor
@Getter
@Setter
@ToString
public class storeReview {

    private String reviewId; //리뷰 id
    private int reviewScore; // 리뷰 점수
    private String review; //리뷰 내용
    private String StoreName; // 식당 이름
    private String user_nickname; // 사용자 닉네임
    SimpleDateFormat formatter = new SimpleDateFormat ( "yyyy.MM.dd HH:mm:ss", Locale.KOREA );
    private Date date = new Date();
    private String writeTime = formatter.format (date);



}