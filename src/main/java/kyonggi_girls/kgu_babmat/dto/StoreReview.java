package kyonggi_girls.kgu_babmat.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class StoreReview {
    private String email; // 이메일
    private String username; // 유저 이름
    private int reviewScore; // 리뷰 점수
    private String review; //리뷰 내용
    private String storeName; //식당 이름
    private String selectStore; // 상위 식당 이름
    private String writeTime; // 작성 시간
    private String menu; // 식당 메뉴
    private List<String> tags;
    private String search;
}