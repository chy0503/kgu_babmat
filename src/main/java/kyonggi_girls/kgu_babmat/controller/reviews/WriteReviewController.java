package kyonggi_girls.kgu_babmat.controller.reviews;



import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kyonggi_girls.kgu_babmat.dto.StoreReview;
import kyonggi_girls.kgu_babmat.dto.User;
import kyonggi_girls.kgu_babmat.service.ReviewService;
import kyonggi_girls.kgu_babmat.service.StoreService;
import kyonggi_girls.kgu_babmat.session.SessionConst;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



import java.util.List;

@Controller
public class WriteReviewController {
    private final ReviewService reviewService;
    private final StoreService storeService;

    public WriteReviewController(ReviewService reviewService, StoreService storeService) {
        this.reviewService = reviewService;
        this.storeService = storeService;
    }

    @GetMapping( "writeReview" )
    public String reviewPage(@RequestParam(value = "storeName", required = false) String storeName, @RequestParam(value = "selectStoreName", required = false) String selectStoreName,
                      Model model, HttpServletRequest request) throws Exception {
//
        // session
        HttpSession session = request.getSession(false);
        if (session == null)
            return "redirect:/";
        User user = (User) session.getAttribute(SessionConst.sessionId);
        model.addAttribute("user", user);
        List storeReview = reviewService.showReview_all(user.getEmail());
        model.addAttribute("storeReview", storeReview);

        model.addAttribute("selectStoreName", selectStoreName);
        model.addAttribute("storeName", storeName);

        return "reviews/writeReview";
    }


    @PostMapping( "/reviewCreate")
    public String writeReview(@ModelAttribute StoreReview storeReview,
                               HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession(false);
        if (session == null)
            return "redirect:/";
        User user = (User) session.getAttribute(SessionConst.sessionId);
        reviewService.updateReview(user.getEmail(), storeReview.getStoreName(), storeReview.getSelectStore(), storeReview.getMenu(), storeReview.getReviewScore(), storeReview.getReview());
        System.out.println(storeReview.getSelectStore());
        return "redirect:/review";
    }

}
