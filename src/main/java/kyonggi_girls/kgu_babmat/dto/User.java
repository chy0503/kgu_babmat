package kyonggi_girls.kgu_babmat.dto;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class User{
    private String email;
    private String username;

    @Builder
    public User(String email, String username) {

        this.email = email;
        this.username = username;
    }
}
