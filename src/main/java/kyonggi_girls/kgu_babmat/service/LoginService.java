package kyonggi_girls.kgu_babmat.service;

import kyonggi_girls.kgu_babmat.domain.model.Member;
import kyonggi_girls.kgu_babmat.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    public Member login(String email, String username) {
        return memberRepository.findByEmail(email)
                .filter(member -> member.getUsername().equals(username))
                .orElse(null);
    }
}
