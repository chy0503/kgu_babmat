package kyonggi_girls.kgu_babmat.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kyonggi_girls.kgu_babmat.domain.model.Member;
import kyonggi_girls.kgu_babmat.dto.Store;
import kyonggi_girls.kgu_babmat.repository.MemberRepository;
import kyonggi_girls.kgu_babmat.service.StoreService;
import kyonggi_girls.kgu_babmat.session.SessionConst;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Controller
public class MainController {
    private final MemberRepository memberRepository;
    private final StoreService storeService;

    public MainController(MemberRepository memberRepository, StoreService storeService) {
        this.memberRepository = memberRepository;
        this.storeService = storeService;
    }

    @GetMapping("main")
    public String main(HttpServletRequest request, Model model) throws ExecutionException, InterruptedException {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "login";
        }

        String memberId = (String) session.getAttribute(SessionConst.sessionId);
        Optional<Member> findMemberOptional = memberRepository.findByMemberId(memberId);
        Member member = findMemberOptional.orElse(null);

        if (member == null) {
            return "login";
        }

        model.addAttribute("member", member);
        List<Store> storeList = storeService.getStores();
        model.addAttribute("storeList", storeList);
        return "main";
    }
}
