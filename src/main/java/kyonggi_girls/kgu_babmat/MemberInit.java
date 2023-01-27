package kyonggi_girls.kgu_babmat;

import kyonggi_girls.kgu_babmat.domain.model.Member;
import kyonggi_girls.kgu_babmat.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class MemberInit {

    private final MemberRepository memberRepository;

    @PostConstruct
    public void memberInit() {
        Member member = new Member("sohyun", "1234");
        memberRepository.save(member);

        Member member1 = new Member("chy0503", "5678");
        memberRepository.save(member1);
    }

}
