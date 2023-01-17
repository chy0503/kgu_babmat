package kyonggi_girls.kgu_babmat.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class storeReview {

    private long reviewId; //리뷰 id
    private String reviewScore; // 리뷰 점수
    private String review; //리뷰 내용
    private String StoreName; // 식당 이름
    private String user_nickname; // 사용자 닉네임
    private Date writeTime; // 작성 시간


}