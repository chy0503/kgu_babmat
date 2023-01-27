package kyonggi_girls.kgu_babmat.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kyonggi_girls.kgu_babmat.domain.model.Member;
import kyonggi_girls.kgu_babmat.repository.MemberRepository;
import kyonggi_girls.kgu_babmat.service.LoginService;
import kyonggi_girls.kgu_babmat.session.SessionConst;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final MemberRepository memberRepository;

    private final LoginService loginService;

    @GetMapping("login")
    public String home(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);

        if(session == null) {
            return "login";
        }

        String memberId = (String)session.getAttribute(SessionConst.sessionId);
        Optional<Member> findMemberOptional = memberRepository.findByMemberId(memberId);
        Member member = findMemberOptional.orElse(null);

        if(member == null) {
            return "login";
        }

        model.addAttribute("member", member);
        return "main";
    }


    @PostMapping("/login")
    public String login(@ModelAttribute Member member, HttpServletRequest request) {
        Member loginMember = loginService.login(member.getMemberId(), member.getPassword());

        if(loginMember == null) {
            return "redirect:/";
        }

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.sessionId, loginMember.getMemberId());

        return "main";
    }


    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session == null) {
            return "redirect:/";
        }
        session.invalidate();
        return "intro";
    }

}
