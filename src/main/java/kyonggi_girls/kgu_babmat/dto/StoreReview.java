package kyonggi_girls.kgu_babmat.dto;




import lombok.*;




@Data
@NoArgsConstructor
@Getter
@Setter
@ToString
public class StoreReview {

    private int reviewScore; // 리뷰 점수
    private String review; //리뷰 내용
    private String storeName; // 식당 이름
    private String writeTime;
    private String menu;




}