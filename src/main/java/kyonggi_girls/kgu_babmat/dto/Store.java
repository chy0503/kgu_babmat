package kyonggi_girls.kgu_babmat.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Store {
    private String name;
    private String businessHours;
    private String description;
    private boolean selectStore;
}
