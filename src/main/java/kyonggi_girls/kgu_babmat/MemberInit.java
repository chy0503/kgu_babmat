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
        Member member = new Member("pshsh0516@kyonggi.ac.kr", "sohyun");
        memberRepository.save(member);

        Member member1 = new Member("202015006@kyonggi.ac.kr", "chy0503");
        memberRepository.save(member1);
    }

}
