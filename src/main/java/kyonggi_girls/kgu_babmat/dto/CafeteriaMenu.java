package kyonggi_girls.kgu_babmat.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class CafeteriaMenu {
    private String date;        // 날짜
    private String breakfast;   // 아침 메뉴
    private String lunch;       // 점심 메뉴
    private String dinner;      // 저녁 메뉴
}
