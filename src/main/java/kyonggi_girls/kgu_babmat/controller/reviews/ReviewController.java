package kyonggi_girls.kgu_babmat.controller.reviews;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kyonggi_girls.kgu_babmat.dao.ReviewDao;
import kyonggi_girls.kgu_babmat.dao.UserDao;
import kyonggi_girls.kgu_babmat.dao.store.StoreDao;
import kyonggi_girls.kgu_babmat.dto.Store;
import kyonggi_girls.kgu_babmat.dto.StoreReview;
import kyonggi_girls.kgu_babmat.dto.User;
import kyonggi_girls.kgu_babmat.session.SessionConst;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.*;
import java.util.concurrent.ExecutionException;


@Controller
public class ReviewController {

    private final ReviewDao reviewDao;
    private final UserDao userDao;
    private final StoreDao storeDao;

    public ReviewController(ReviewDao reviewDao, UserDao userDao, StoreDao storeDao) {
        this.reviewDao = reviewDao;
        this.userDao = userDao;
        this.storeDao = storeDao;
    }


    @GetMapping("review")
    public String myReview(@ModelAttribute StoreReview storeReview, Model model, HttpServletRequest request, @RequestParam(required = false) String selectStoreName, @RequestParam String storeName) throws ExecutionException, InterruptedException {
        // session
        HttpSession session = request.getSession(false);
        if (session == null)
            return "redirect:/";

        User user = (User) session.getAttribute(SessionConst.sessionId);
        model.addAttribute("user", user);

        // store
        if ((selectStoreName == null) || (selectStoreName == "")) { // 일반 식당일 경우
            List<Store> store = storeDao.getStore(storeName);
            model.addAttribute("store", store);
            model.addAttribute("selectStoreName", null);
        } else { // 푸드코트 내의 식당일 경우
            List<Store> store = storeDao.getInnerStore(selectStoreName, storeName);
            model.addAttribute("store", store);
            model.addAttribute("selectStoreName", selectStoreName);
        }
        model.addAttribute("storeName", storeName);

        // store review
        List<StoreReview> storeReviews = reviewDao.showReview_all_store(storeName);
        for (StoreReview sr : storeReviews) {
            sr.setUsername(userDao.getUser(sr.getEmail()).getUsername());
        }
        model.addAttribute("storeReviews", storeReviews);
        return "reviews/review";
    }
}

