package kyonggi_girls.kgu_babmat.dto;




import lombok.*;




@Data
@NoArgsConstructor
@Getter
@Setter
@ToString
public class StoreReview {
    private String email;
    private int reviewScore; // 리뷰 점수
    private String review; //리뷰 내용
    private String storeName;
    private String selectStore;
    private String writeTime;
    private String menu;


}