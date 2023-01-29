package kyonggi_girls.kgu_babmat.domain.model;

import lombok.Data;

@Data
public class Member {
    private String email;
    private String username;

    public Member(String email, String username) {
        this.email = email;
        this.username = username;
    }
}
