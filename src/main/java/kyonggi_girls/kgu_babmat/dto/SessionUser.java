package kyonggi_girls.kgu_babmat.dto;

import lombok.Getter;
import java.io.Serializable;
@Getter
public class SessionUser implements Serializable {

    private String username;
    private String email;

    public SessionUser(User user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
    }
}
