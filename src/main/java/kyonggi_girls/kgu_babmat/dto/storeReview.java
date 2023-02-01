package kyonggi_girls.kgu_babmat.dto;



import lombok.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


@Data
@NoArgsConstructor
@Getter
@Setter
@ToString
public class storeReview {

    private int reviewScore; // 리뷰 점수
    private String review; //리뷰 내용
    private String StoreName; // 식당 이름
    private String email; // 사용자 email
    SimpleDateFormat formatter = new SimpleDateFormat ( "yyyy.MM.dd HH:mm:ss", Locale.KOREA );
    private Date date = new Date();
    private String writeTime = formatter.format (date);

    private String menu;





}