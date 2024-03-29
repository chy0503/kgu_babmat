package kyonggi_girls.kgu_babmat.controller.reviews;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kyonggi_girls.kgu_babmat.dao.ReviewDao;
import kyonggi_girls.kgu_babmat.dto.StoreReview;
import kyonggi_girls.kgu_babmat.dto.User;
import kyonggi_girls.kgu_babmat.session.SessionConst;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WriteReviewController {
    private final ReviewDao reviewDao;

    public WriteReviewController(ReviewDao reviewDao) {
        this.reviewDao = reviewDao;
    }

    @PostMapping("/reviewCreate")
    public String writeReview( @ModelAttribute StoreReview storeReview, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession(false);
        if (session == null) return "redirect:/";
        User user = (User) session.getAttribute(SessionConst.sessionId);

        reviewDao.createReview(user.getEmail(), storeReview.getStoreName(), storeReview.getSelectStore(), storeReview.getMenu(), storeReview.getReviewScore(), storeReview.getTags(), storeReview.getReview(), storeReview.getWriteTime());
        return "redirect:/main";
    }
}
