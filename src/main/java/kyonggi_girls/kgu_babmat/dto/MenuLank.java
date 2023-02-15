package kyonggi_girls.kgu_babmat.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class MenuLank {
    private String name;
    private float reviewScore;
    private int reviewNum;
    private String storeName;
    private String selectStore;
}