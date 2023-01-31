package kyonggi_girls.kgu_babmat.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Like {
    private String menu;
    private long price;
    private String selectStore;
    private String store;
}
