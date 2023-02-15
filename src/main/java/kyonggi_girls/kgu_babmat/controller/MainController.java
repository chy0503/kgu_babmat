package kyonggi_girls.kgu_babmat.controller;

import com.google.cloud.firestore.QueryDocumentSnapshot;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kyonggi_girls.kgu_babmat.dto.Menu;
import kyonggi_girls.kgu_babmat.dto.Store;
import kyonggi_girls.kgu_babmat.dto.User;
import kyonggi_girls.kgu_babmat.service.ReviewService;
import kyonggi_girls.kgu_babmat.service.StoreService;
import kyonggi_girls.kgu_babmat.session.SessionConst;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Controller
public class MainController {
    private final StoreService storeService;
    private final ReviewService reviewService;

    public MainController(StoreService storeService, ReviewService reviewService) {
        this.storeService = storeService;
        this.reviewService = reviewService;
    }

    @GetMapping("main")
    public String main(HttpServletRequest request, Model model) throws ExecutionException, InterruptedException {
        // session
        HttpSession session = request.getSession(false);
        if (session == null)
            return "redirect:/";

        User user = (User) session.getAttribute(SessionConst.sessionId);
        model.addAttribute("user", user);

        // store list
        List<Store> storeList = storeService.getStores();
        model.addAttribute("storeList", storeList);

        List<Menu> menuLanking = storeService.showMenuLanking();
        model.addAttribute("menuLanking", menuLanking);
        return "main";
    }


}
